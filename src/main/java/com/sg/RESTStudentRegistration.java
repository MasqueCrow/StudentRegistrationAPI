package com.sg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RESTStudentRegistration {	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RESTStudentRegistration.class, args);
	}

}
