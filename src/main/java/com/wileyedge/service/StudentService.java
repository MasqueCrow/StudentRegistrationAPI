package com.wileyedge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wileyedge.dao.StudentRepository;
import com.wileyedge.model.Student;
import com.wileyedge.restfulservice.user.User;

@Service
public class StudentService implements IService {
	@Autowired
	private StudentRepository dao; 
	
	public StudentService() {
		System.out.println("Inside constructor of StudentService");
	}
	
	@Override
	public List<Student> getAllStudents() {
		return dao.findAll();
	}
	
	@Override
	public Student getStudent(int id) {
		System.out.println("Inside getStudent of Student Service");
		Optional<Student> student = dao.findById(id);
		
		if(student.isPresent()) {
			return student.get();
		}
		
		return null;
		
	}
	
	public Student saveStudent(Student stud) {
		System.out.println("Inside saveStudent of Student Service");
		return dao.save(stud);
	}

	public void removeStudent(int id) {
		System.out.println("Inside removeStudent of Student Service");
		dao.deleteById(id);
	}
	
	//custom method
	public List<Student> findStudentByName(String name) {
		System.out.println("Inside findStudentByName of Student Service");
		return dao.findByName(name);
	}




}
