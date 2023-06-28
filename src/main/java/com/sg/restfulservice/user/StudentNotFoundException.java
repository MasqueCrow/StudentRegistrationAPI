package com.sg.restfulservice.user;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Response
public class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException(String msg) {
		super(msg);
	}
}
