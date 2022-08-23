package com.reactor.reactiveprogrammingdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reactor.exception.BookException;
import com.reactor.services.BookInfoService;
import com.reactor.services.BookService;
import com.reactor.services.ReviewService;

import lombok.experimental.var;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
public class BookServiceMockTest {
	
	
	@Mock
	private BookInfoService bookInfoService;
	
	
	@Mock
	private ReviewService reviewService;
	
	
	@InjectMocks
	private BookService bookService;
	
	
	@Test
	void getBooksMock(){
		
		Mockito.when(bookInfoService.getBooks())
			.thenCallRealMethod();
		
		Mockito.when(reviewService.getReviews(Mockito.anyLong()))
			.thenCallRealMethod();
		
		var books = bookService.getAllBooks();
		
		
		StepVerifier.create(books)
			.expectNextCount(3)
			.verifyComplete();
		
		
	}
	
	
	@Test
	void getBooksMockOnError(){
		
		Mockito.when(bookInfoService.getBooks())
			.thenCallRealMethod();
		
		Mockito.when(reviewService.getReviews(Mockito.anyLong()))
			.thenThrow(new IllegalStateException("exception in test"));
		
		var books = bookService.getAllBooks();
		
		
		StepVerifier.create(books)
			.expectError(BookException.class)
			.verify();
		
		
	}
	
	
	@Test
	void getBooksMockOnErrorRetry(){
		
		Mockito.when(bookInfoService.getBooks())
			.thenCallRealMethod();
		
		Mockito.when(reviewService.getReviews(Mockito.anyLong()))
			.thenThrow(new IllegalStateException("exception in test"));
		
		var books = bookService.getAllBooksRetry();
		
		
		StepVerifier.create(books)
			.expectError(BookException.class)
			.verify();
		
		
	}
	
	
	@Test
	void getBooksMockOnErrorRetryWhen(){
		
		Mockito.when(bookInfoService.getBooks())
			.thenCallRealMethod();
		
		Mockito.when(reviewService.getReviews(Mockito.anyLong()))
			.thenThrow(new IllegalStateException("exception in test"));
		
		var books = bookService.getAllBooksRetryWhen();
		
		
		StepVerifier.create(books)
			.expectError(BookException.class)
			.verify();
		
		
	}

}
