package com.sg.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.model.Student;
import com.sg.myexception.ResourceNotFoundException;
import com.sg.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentResource {
	private final Logger LOG = LoggerFactory.getLogger(StudentResource.class);
	private StudentService service;
	
	@Autowired
	public StudentResource(StudentService service) {
		this.service = service;
		LOG.info("Inside constructor of StudentResource");
	}

	@GetMapping(path = "/all") // better annotation
	public ResponseEntity<List<Student>>  getAllStudents() {
		LOG.info("Inside getAllStudents of StudentResource");
		
		List<Student> studList = service.getAllStudents();
		
		if(studList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(studList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Student> retrieveStudent(@PathVariable int id) {
		LOG.info("Inside retrieveStudent of StudentResource " + id);
		
		Student stud = service.getStudent(id);
		
		if(stud == null) {
			LOG.warn("ResourceNotFoundException is being thrown");
			throw new ResourceNotFoundException("Student not found with id= " + id);
		}
		return new ResponseEntity<>(stud, HttpStatus.OK);
	}
	
	@PostMapping(path = "/new")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student stud, BindingResult result, Model model) {
		LOG.info("Inside createStudent of StudentResource");
		
		if(result.hasErrors()) {
			LOG.error("createStudent has following error {}", result.getFieldError().toString());
			return new ResponseEntity<>(stud, HttpStatus.BAD_REQUEST);
		}
		service.saveStudent(stud);
		LOG.info("Current student created: {}", stud.getName());
		return  new ResponseEntity<>(stud, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
	    LOG.info("Inside deleteStudent of StudentResource");
	    Student stud = service.getStudent(id);
	    
	    if (stud == null) {
	        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
	    }
	    
	    service.removeStudent(id);
	    String msg = "Student " + stud.getName() + " record deleted";

	    LOG.info(msg);
	    return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "update/{id}")
	public ResponseEntity<String> updateStudent(@Valid @RequestBody Student stud, @PathVariable int id) {
		Student found_stud = service.getStudent(id);
		String msg = "";
		
		if(found_stud != null) {
			service.updateStudent(stud);
			msg = "Student " + stud.getName() + " record updated";
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		}
		else {
			msg = "Student with id=" + id + " not updated"; 
		}

		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	}
	
	//custom method to get list of student with duplicate names
	@GetMapping(path = "/name/{name}")
	public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name) {
		LOG.info("Inside getStudentsByName of getStudentsByName");
		List<Student> students = service.findStudentByName(name);
		
		if(students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
}
