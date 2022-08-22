package com.reactor.reactiveprogrammingdemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.reactor.services.BookInfoService;
import com.reactor.services.BookService;
import com.reactor.services.ReviewService;

import lombok.experimental.var;

class BookServicetest {

	private BookInfoService bookInfoService = new BookInfoService();
	private ReviewService reviewService = new ReviewService();
	private BookService bookService = new BookService(bookInfoService,reviewService);

	@Test
	void getAllBooks() {

		
		
		
		
	}

}
