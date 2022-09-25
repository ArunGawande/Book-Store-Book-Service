package com.bridgelabz.bookstorebookservice.controller;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import com.bridgelabz.bookstorebookservice.service.IBookService;
import com.bridgelabz.bookstorebookservice.util.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Purpose : BookService Data API's
 */

@RestController
@RequestMapping("/bookdetails")
public class BookServiceController {
	@Autowired
	IBookService bookService;
	
	/*
     *@Purpose : add book Api
     *@Param : bookDTO and token
     */
	
	@PostMapping("/addbook")
	public ResponseEntity<BookResponse> addBook(@RequestBody BookServiceDTO bookDTO, @RequestHeader String token) {
		BookServiceModel bookModel = bookService.addBook(bookDTO, token);
		BookResponse bookResponse = new BookResponse(200, "Book Added Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose :update book details
     *@Param : bookId, bookDTO and token
     */
	
	@PutMapping("/updatebook/{bookId}")
	public ResponseEntity<BookResponse> updatebook(@PathVariable Long bookId, @RequestBody BookServiceDTO bookDTO, @RequestHeader String token) {
		BookServiceModel bookModel = bookService.updateBook(bookId, bookDTO, token);
		BookResponse bookResponse = new BookResponse(200, "Book Updated Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : get all book details
     *@Param : token
     */
	
	@GetMapping("/fetchallbooks")
	public ResponseEntity<BookResponse> fetchAllBooks(@RequestHeader String token) {
		List<BookServiceModel> bookModel = bookService.fetchAllBooks(token);
		BookResponse bookResponse = new BookResponse(200, "Fetch All Books Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose :  fetch book details
     *@Param : bookId, token
     */
	
	@GetMapping("/fetchbookby/{bookId}")
	public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId, @RequestHeader String token) {
		BookServiceModel bookModel = bookService.getBook(bookId, token);
		BookResponse bookResponse = new BookResponse(200, "Fetch All Books Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : delete book
     *@Param : bookId, token
     */
	
	@DeleteMapping("/deletebook/{bookId}")
	public ResponseEntity<BookResponse> deletebook(@PathVariable Long bookId, @RequestHeader String token) {
		BookServiceModel bookModel = bookService.deletebook(bookId, token);
		BookResponse bookResponse = new BookResponse(200, "Delete Book Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : update quantity of book
     *@Param : bookId, bookQuantity and token
     */
	
	@PutMapping("/updatequantity/{bookId}")
	public ResponseEntity<BookResponse> changeQuantity(@PathVariable Long bookId,@RequestParam Integer bookQuantity,  @RequestHeader String token) {
		BookServiceModel bookModel = bookService.changeQuantity(bookId, bookQuantity, token);
		BookResponse bookResponse = new BookResponse(200, "Quantity Updated Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose :update quantity
     *@Param : bookId, bookPrice and token
     */
	
	@PutMapping("/updateprice/{bookId}")
	public ResponseEntity<BookResponse> updatePrice(@PathVariable Long bookId,@RequestParam Integer bookPrice,  @RequestHeader String token) {
		BookServiceModel bookModel = bookService.updatePrice(bookId, bookPrice, token);
		BookResponse bookResponse = new BookResponse(200, "Price Updated Successfully", bookModel);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose :validate bookId
     *@Param : bookId
     */
	
	@GetMapping("/validatebookId/{bookId}")
	public BookResponse validateBookId(@PathVariable Long bookId) {
		return bookService.validateBookId(bookId);
	}
	
	/*
     *@Purpose :increase quantity of books
     *@Param : bookId, bookQuantity
     */
	
	@GetMapping("/updatebookquantity/{bookId}/{bookQuantity}")
	public BookResponse updateBookQuantity(@PathVariable Long bookId, @PathVariable Integer bookQuantity) {
		BookResponse book =bookService.updateBookQuantity(bookId,bookQuantity);
		return new BookResponse(200, "user found", book);
	}
	
	/*
     *@Purpose : decrease quantity of books
     *@Param : bookId, bookQuantity
     */
	
	@GetMapping("/updatequantity/{bookId}/{bookQuantity}")
	public BookResponse updateQuantity(@PathVariable Long bookId, @PathVariable Integer bookQuantity) {
		BookResponse book =bookService.updateQuantity(bookId,bookQuantity);
		return new BookResponse(200, "user found", book);
	}
}
