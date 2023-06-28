package com.sg.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(generator = "increment")
	private int id;
	
	@Size(min=3, message="Name must have atleast 3 chars")
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "mobile_no")
	private int mobileNo;
	
	@Column(name = "city")
	private String city;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjects;
	
	//default constructor needs to be added for creating bean
	//otherwise exception occurs
	public Student() {
		
	}
	
	public Student(int id, String name, int age, int mobileNo, String city) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.mobileNo = mobileNo;
		this.city = city;
	}
	
	public Student(String name, int age, int mobileNo, String city) {
		this.name = name;
		this.age = age;
		this.mobileNo = mobileNo;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public void addSubject(Subject subject) {
		if (subjects == null) {
			subjects = new ArrayList<>();
		}
		subjects.add(subject);
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	
}
