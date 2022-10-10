package com.wileyedge;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = {"controller","service","model", "repository"})
@SpringBootApplication
public class RESTStudentRegistration {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RESTStudentRegistration.class, args);
		System.out.println("Hello World");
		
		EntityManagerFactory factory = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		
		System.out.println("factory " + factory);
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		System.out.println("Hello World");
		tx.begin();
		tx.commit();
		em.close();
	}

}
