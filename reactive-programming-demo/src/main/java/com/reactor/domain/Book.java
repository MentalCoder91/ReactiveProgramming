package com.reactor.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private BookInfo bookInfo;
	private List<Review> reviews;
	
	public Book(BookInfo bookInfo, List<Review> reviews) {
		super();
		this.bookInfo = bookInfo;
		this.reviews = reviews;
	}
	
	
	
	
}
