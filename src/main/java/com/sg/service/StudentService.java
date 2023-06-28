package com.sg.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.controller.StudentResource;
import com.sg.dao.StudentRepository;
import com.sg.model.Student;

@Service
public class StudentService implements IService {
	private final Logger LOG = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository dao; 
	
	public StudentService() {
		LOG.info("Inside constructor of StudentService");
	}
	
	@Override
	public List<Student> getAllStudents() {
		return dao.findAll();
	}
	
	@Override
	public Student getStudent(int id) {
		LOG.info("Inside getStudent of Student Service");
		Optional<Student> student = dao.findById(id);
		
		if(student.isPresent()) {
			return student.get();
		}
		
		return null;
	}
	
	public Student saveStudent(Student stud) {
		LOG.info("Inside saveStudent of Student Service");
		return dao.save(stud);
	}

	public void removeStudent(int id) {
		LOG.info("Inside removeStudent of Student Service");
		dao.deleteById(id);
	}
	
	//custom method
	public List<Student> findStudentByName(String name) {
		LOG.info("Inside findStudentByName of Student Service");
		return dao.findByName(name);
	}

	public int updateStudent(Student stud) {
		int id = stud.getId();
		int age = stud.getAge();
		int mobile_no = stud.getMobileNo();
		String name = stud.getName();
		String city = stud.getCity();
		
		return dao.setStudent(name, age, mobile_no, city, id);
	}



}
