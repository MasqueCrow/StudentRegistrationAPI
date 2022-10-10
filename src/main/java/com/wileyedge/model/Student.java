package com.wileyedge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

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
	
	@Column(name = "address")
	private String address;
	
	//default constructor needs to be added for creating bean
	//otherwise exception occurs
	public Student() {
		
	}
	
	public Student(int id, String name, int age, int mobileNo, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.mobileNo = mobileNo;
		this.address = address;
	}
	
	public Student(String name, int age, int mobileNo, String address) {
		this.name = name;
		this.age = age;
		this.mobileNo = mobileNo;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
