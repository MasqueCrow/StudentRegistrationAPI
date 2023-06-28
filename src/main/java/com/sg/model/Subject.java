package com.sg.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="subject")
public class Subject {
	@Id
	@GeneratedValue(generator = "increment")
	private int id;
	
	private String name;
	
	@ManyToMany
    @JoinTable(name = "student_subject",
               joinColumns = @JoinColumn(name = "subject_id"),
               inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public void setName(String name) {
        this.name = name;
    }
    
    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
