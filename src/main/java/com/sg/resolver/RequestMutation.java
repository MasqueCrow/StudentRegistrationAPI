package com.sg.resolver;

import org.springframework.stereotype.Component;

import com.sg.dao.StudentRepository;
import com.sg.dao.SubjectRepository;
import com.sg.model.Student;
import com.sg.model.Subject;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class RequestMutation implements GraphQLMutationResolver {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public RequestMutation(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Student createStudent(String name) {
        Student student = new Student();
        student.setName(name);
        return studentRepository.save(student);
    }

    public Subject createSubject(String name) {
        Subject subject = new Subject();
        subject.setName(name);
        return subjectRepository.save(subject);
    }

    public Student assignSubjectToStudent(Integer studentId, Integer subjectId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        if (student != null && subject != null) {
            student.addSubject(subject);
            studentRepository.save(student);
        }

        return student;
    }
}
