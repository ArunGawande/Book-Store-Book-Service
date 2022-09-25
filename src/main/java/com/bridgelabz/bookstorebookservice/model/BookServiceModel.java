package com.bridgelabz.bookstorebookservice.model;

import com.bridgelabz.bookstorebookservice.dto.BookServiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookServiceModel {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;
    @Column(name = "bookName")
    private String bookName;
    @Column  (name = "bookAuthor")
    private String bookAuthor;
    @Column (name = "bookDescription")
    private String bookDescription;  
    @Lob
	private byte[] bookLogo;
    @Column (name = "bookPrice")
    private int bookPrice;
    @Column (name = "bookQuantity")
    private int bookQuantity;
    
    public BookServiceModel(BookServiceDTO bookDTO) {
    	this.bookName = bookDTO.getBookName();
    	this.bookAuthor = bookDTO.getBookAuthor();
    	this.bookDescription = bookDTO.getBookDescription();
    	this.bookPrice = bookDTO.getBookPrice();
    	this.bookQuantity = bookDTO.getBookQuantity();
    }
}
