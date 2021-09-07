package com.lms.books.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lms.books.customexceptions.IdMismatchException;
import com.lms.books.customexceptions.BookNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public Map<String,String> handleMethodArguments(MethodArgumentNotValidException exception){
	Map<String,String> errors=new HashMap<>();
	StringBuilder message=new StringBuilder();
	exception.getBindingResult().getAllErrors().forEach(error->{
	message.append(error.getDefaultMessage()).append(", ");
	});
	errors.put("timestamp", new Date().toString());
	errors.put("status",HttpStatus.BAD_REQUEST.name());
	errors.put("details", message.toString());
	return errors;
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<String> handleBookNotFound(BookNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IdMismatchException.class)
	public ResponseEntity<String> handleArgumentsMismatch(IdMismatchException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
