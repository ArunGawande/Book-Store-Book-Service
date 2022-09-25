package com.bridgelabz.bookstorebookservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BookDetailsNotFoundException extends RuntimeException{
	private int statusCode;
	private String message;
	
	public BookDetailsNotFoundException(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	
}
