package com.epam.restcontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
