package com.reactor.reactiveprogrammingdemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.reactor.services.BookInfoService;
import com.reactor.services.BookService;
import com.reactor.services.ReviewService;

import lombok.experimental.var;
import reactor.test.StepVerifier;

class BookServicetest {

	private BookInfoService bookInfoService = new BookInfoService();
	private ReviewService reviewService = new ReviewService();
	private BookService bookService = new BookService(bookInfoService,reviewService);

	@Test
	void getAllBooks() {

		
		
		var books = bookService.getAllBooks();
		StepVerifier.create(books)
			.assertNext(book->{
				
				assertEquals("Book 1", book.getBookInfo().getTitle());
				assertEquals(2,book.getReviews().size());
				
			})
			.assertNext(book->{
				
				assertEquals("Book 2", book.getBookInfo().getTitle());
				assertEquals(2,book.getReviews().size());
				
			})
			.assertNext(book->{
				
				assertEquals("Book 3", book.getBookInfo().getTitle());
				assertEquals(2,book.getReviews().size());
				
			})
			.verifyComplete();
		
		
		
	}
	
	
	

	@Test
	void getBookById() {
		
		var book = bookService.getBookById(1).log();
		
		StepVerifier
			.create(book)
			.assertNext(b->{
				assertEquals("Book 1", b.getBookInfo().getTitle());
				assertEquals(2,b.getReviews().size());
				
			})
			.verifyComplete();
		
	}
	
	
	
	

}
