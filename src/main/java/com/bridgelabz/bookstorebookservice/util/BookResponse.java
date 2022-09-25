package com.bridgelabz.bookstorebookservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
	private int errorCode;
	private String message;
	private Object object;
}
