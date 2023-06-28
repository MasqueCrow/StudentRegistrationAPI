package com.sg.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sg.dao.StudentRepository;
import com.sg.dao.SubjectRepository;
import com.sg.model.Student;
import com.sg.model.Subject;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class RequestQuery implements GraphQLQueryResolver {
	private final StudentRepository studentRepository ;
	private final SubjectRepository subjectRepository;
	
	public RequestQuery(StudentRepository studentRepository, SubjectRepository subjectRepository) {
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
	}
	
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubject(Integer id) {
        return subjectRepository.findById(id).orElse(null);
    }
	
}
