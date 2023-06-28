package com.sg.restfulservice.user;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
//@ControllerAdvice //both annotations will work
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
	public final ExceptionResponse handleAllExceptions(Exception ex, WebRequest req) {
		ExceptionResponse expResp = new ExceptionResponse(new Date(),ex.getMessage(),"Detail Description of the Exception");
		return expResp;
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest req) {
	public final ExceptionResponse handleUserNotFoundException(StudentNotFoundException ex, WebRequest req) {
		ExceptionResponse expResp = new ExceptionResponse(new Date(),ex.getMessage(),"The Requested User ID is not present in the system");
		return expResp;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse expresponse = new ExceptionResponse(new Date(), "validation failed", ex.getBindingResult().toString());
		return new ResponseEntity(expresponse,HttpStatus.BAD_REQUEST);
	}
	
}
