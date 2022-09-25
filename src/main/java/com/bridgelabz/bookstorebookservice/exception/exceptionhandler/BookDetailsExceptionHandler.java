package com.bridgelabz.bookstorebookservice.exception.exceptionhandler;

import com.bridgelabz.bookstorebookservice.exception.BookDetailsNotFoundException;
import com.bridgelabz.bookstorebookservice.util.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class BookDetailsExceptionHandler {
	@ExceptionHandler(BookDetailsNotFoundException.class)
	 public ResponseEntity<BookResponse> response(BookDetailsNotFoundException bookNotFoundException) {
		BookResponse bookResponse = new BookResponse();
		bookResponse.setErrorCode(400);
        bookResponse.setMessage(bookNotFoundException.getMessage());
        return new ResponseEntity<>(bookResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(value = Exception.class)
    public ResponseEntity<BookResponse>
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		BookResponse bookResponse = new BookResponse();
		bookResponse.setErrorCode(500);
		bookResponse.setMessage(e.getMessage());
        return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
