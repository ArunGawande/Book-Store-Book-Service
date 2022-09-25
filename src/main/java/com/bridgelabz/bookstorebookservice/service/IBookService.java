package com.bridgelabz.bookstorebookservice.service;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import com.bridgelabz.bookstorebookservice.util.BookResponse;

import java.util.List;

public interface IBookService {

	BookServiceModel addBook(BookServiceDTO bookDTO, String token);

	BookServiceModel updateBook(Long bookId, BookServiceDTO bookDTO, String token);

	List<BookServiceModel> fetchAllBooks(String token);

	BookServiceModel getBook(Long bookId, String token);

	BookServiceModel deletebook(Long bookId, String token);

	BookServiceModel changeQuantity(Long bookId, Integer bookQuantity, String token);

	BookServiceModel updatePrice(Long bookId, Integer bookPrice, String token);

	BookResponse validateBookId(Long bookId);

	BookResponse updateBookQuantity(Long bookId, Integer bookQuantity);

	BookResponse updateQuantity(Long bookId, Integer bookQuantity);
}
