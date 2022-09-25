package com.bridgelabz.bookstorebookservice.service;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import com.bridgelabz.bookstorebookservice.exception.BookDetailsNotFoundException;
import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import com.bridgelabz.bookstorebookservice.repository.BookServiceRepository;
import com.bridgelabz.bookstorebookservice.util.BookResponse;
import com.bridgelabz.bookstorebookservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/*
 * @Purpose :  BookService implementation
 */

@Service
public class BookService implements IBookService {
	@Autowired
	BookServiceRepository bookServiceRepository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	TokenUtil tokenUtil;

	/*
	 *  @Purpose:add-book details to BookStore
	 */

	@Override
	public BookServiceModel addBook(BookServiceDTO bookDTO, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8081/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			BookServiceModel bookModel = new BookServiceModel(bookDTO);
			bookServiceRepository.save(bookModel);
			return bookModel;
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/*
	 *  @Purpose:update details
	 */

	@Override
	public BookServiceModel updateBook(Long bookId, BookServiceDTO bookDTO, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8081/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookServiceModel> isBookPresent = bookServiceRepository.findById(bookId);
			if (isBookPresent.isPresent()) {
				isBookPresent.get().setBookName(bookDTO.getBookName());
				isBookPresent.get().setBookDescription(bookDTO.getBookDescription());
				isBookPresent.get().setBookAuthor(bookDTO.getBookAuthor());
				bookServiceRepository.save(isBookPresent.get());
				return isBookPresent.get();
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/*
	 *  @Purpose:books Details from BookStore
	 */

	@Override
	public List<BookServiceModel> fetchAllBooks(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8081/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			List<BookServiceModel> checkBooksPresent = bookServiceRepository.findAll();
			if (checkBooksPresent.size()>0) {
				return checkBooksPresent;
			}
			throw new BookDetailsNotFoundException(500, "Book List is Empty");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/*
	 *  @Purpose:get-book details
	 */

	@Override
	public BookServiceModel getBook(Long bookId, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8081/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookServiceModel> isBooksPresent = bookServiceRepository.findById(bookId);
			if (isBooksPresent.isPresent()) {
				return isBooksPresent.get();
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/*
	 *  @Purpose:delete book details
	 */

	@Override
	public BookServiceModel deletebook(Long bookId, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8081/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookServiceModel> isBooksPresent = bookServiceRepository.findById(bookId);
			if (isBooksPresent.isPresent()) {
				bookServiceRepository.delete(isBooksPresent.get());
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/*
	 *  @Purpose:change quantity of book details
	 */

	@Override
	public BookServiceModel changeQuantity(Long bookId, Integer bookQuantity, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8081/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookServiceModel> isBooksPresent = bookServiceRepository.findById(bookId);
			if (isBooksPresent.isPresent()) {
				isBooksPresent.get().setBookQuantity(isBooksPresent.get().getBookQuantity()+bookQuantity);
				bookServiceRepository.save(isBooksPresent.get());
				return isBooksPresent.get();
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}

	/*
	 *  @Purpose:update price of book
	 */

	@Override
	public BookServiceModel updatePrice(Long bookId, Integer bookPrice, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BOOKSTORE-USERSERVICE:8081/bookstoreuser/verify/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookServiceModel> isBooksPresent = bookServiceRepository.findById(bookId);
			if (isBooksPresent.isPresent()) {
				isBooksPresent.get().setBookPrice(bookPrice);
				bookServiceRepository.save(isBooksPresent.get());
				return isBooksPresent.get();
			}
			throw new BookDetailsNotFoundException(500, "Book Not Found");
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}
	/*
	 *  @Purpose:increase quantity of book
	 */

	@Override
	public BookResponse updateBookQuantity(Long bookId, Integer bookQuantity) {
		Optional<BookServiceModel> isBooksPresent = bookServiceRepository.findById(bookId);
		if (isBooksPresent.isPresent()) {
			isBooksPresent.get().setBookQuantity(isBooksPresent.get().getBookQuantity() - bookQuantity);
			bookServiceRepository.save(isBooksPresent.get());
			return new BookResponse(200,"User Update Successfully",isBooksPresent.get());
		}
		throw new BookDetailsNotFoundException(500, "Book Not Found");
	}

	@Override
	public BookResponse updateQuantity(Long bookId, Integer bookQuantity) {
		Optional<BookServiceModel> isBooksPresent = bookServiceRepository.findById(bookId);
		if (isBooksPresent.isPresent()) {
			isBooksPresent.get().setBookQuantity(isBooksPresent.get().getBookQuantity() + bookQuantity);
			bookServiceRepository.save(isBooksPresent.get());
			return new BookResponse(200,"User Update Successfully",isBooksPresent.get());
		}
		throw new BookDetailsNotFoundException(500, "Book Not Found");
	}

	@Override
	public BookResponse validateBookId(Long bookId) {
		Optional<BookServiceModel> isUserPresent = bookServiceRepository.findById(bookId);
		if (isUserPresent.isPresent()) {
			return new BookResponse(200,"User Validate Successfully",isUserPresent.get());
		}
		throw new BookDetailsNotFoundException(500, "User Not Found");
	}
	

}
