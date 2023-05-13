package com.springboot.excepHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exception(Exception exception) {
		return ResponseEntity.badRequest().body(exception.toString());
	}
}
