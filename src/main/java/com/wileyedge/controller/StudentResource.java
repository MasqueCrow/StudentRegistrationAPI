package com.wileyedge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wileyedge.model.Student;
import com.wileyedge.restfulservice.user.User;
import com.wileyedge.restfulservice.user.UserNotFoundException;
import com.wileyedge.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentResource {
	@Autowired
	private StudentService service;
	
	public StudentResource() {
		System.out.println("Inside constructor of StudentResource");
	}

	@GetMapping(path = "/all") // better annotation
	public List<Student> getAllStudents() {
		System.out.println("Inside getAllStudents of StudentResource");
		List<Student> studList = service.getAllStudents();
		System.out.println(studList);
		return studList;
	}
	
	@GetMapping(path = "/{id}")
	public Student retrieveStudent(@PathVariable int id) {
		System.out.println("Inside retrieveStudent of StudentResource " + id);
		Student stud = service.getStudent(id);
		
		if(stud == null) {
			System.out.println("Custom exception is being thrown");
			throw new UserNotFoundException(id + " not found");
		}
		return stud;
	}
	
	@PostMapping(path = "/student")
	public Student createStudent(@Valid @RequestBody Student stud, BindingResult result, Model model) {
		System.out.println("Inside createStudent of StudentResource ");
		if(result.hasErrors()) {
			System.out.println("createStudent has error");
		}
		else {
			System.out.println("no error!!!");
		}
		return service.saveStudent(stud);
	}
	
	@DeleteMapping(path = "/student/{id}")
	public void deleteStudent(@PathVariable int id) {
		System.out.println("Inside deleteStudent of StudentResource ");
		service.removeStudent(id);
	}
	
	//custom method to get list of student with duplicate names
	@GetMapping(path = "/user/name/{name}")
	public List<Student> getStudentsByName(@PathVariable String name) {
		System.out.println("Inside getUsersByName of UserResource");
		List<Student> students = service.findStudentByName(name);
		return students;
	}
	
//	@GetMapping(path = "/test")
//	public String testStudent() {
//		System.out.println("Hello students");
//		return "students";
//	}

}
