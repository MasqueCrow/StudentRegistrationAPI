package com.wileyedge.service;

import java.util.List;

import com.wileyedge.model.Student;

public interface IService {
	Student saveStudent(Student stud);
	List<Student> getAllStudents();
	Student getStudent(int id);
}
