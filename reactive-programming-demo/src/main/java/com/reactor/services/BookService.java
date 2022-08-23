package com.reactor.services;

import java.time.Duration;
import java.util.List;
import org.slf4j.LoggerFactory;
import com.reactor.domain.Book;
import com.reactor.domain.Review;
import com.reactor.exception.BookException;
import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.Retry.RetrySignal;

@Slf4j
public class BookService {
	
	
	 private  Logger logger = (Logger) LoggerFactory.getLogger(BookService.class);

	private BookInfoService bookInfoService;
	private ReviewService reviewService;

	public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
		super();
		this.bookInfoService = bookInfoService;
		this.reviewService = reviewService;
	}

	public BookService() {
	}

	public Flux<Book> getAllBooks() {

		var allBooks = bookInfoService.getBooks();

		return allBooks.flatMap(bookInfo -> {

			Mono<List<Review>> monoList = 
					reviewService.getReviews(bookInfo.getBookId()).collectList();

			return monoList.map(review -> new Book(bookInfo, review));
		
			}).onErrorMap(t -> {

			logger.error("Error in getAllBooks");
			return new BookException("Exception while fertching book");
			})
			.log();

	}
	
	
	
	public Flux<Book> getAllBooksRetry() {

		var allBooks = bookInfoService.getBooks();

		return allBooks.flatMap(bookInfo -> {

					Mono<List<Review>> monoList = 
								reviewService.getReviews(bookInfo.getBookId()).collectList();

					return monoList.map(review -> new Book(bookInfo, review));
		
			})
			.onErrorMap(t -> {
			logger.error("Error in getAllBooks");
			return new BookException("Exception while fetching book with retry");
			})
			.retry(5)
			.log();

	}
	
	public Flux<Book> getAllBooksRetryWhen() {

		var retrySpec = Retry
				.backoff(3,Duration.ofMillis(2000))
				.filter(throwable-> throwable instanceof BookException)
				.onRetryExhaustedThrow((retryBackoff,retrySignal)->{
					
					return Exceptions.propagate(retrySignal.failure());
				});
		var allBooks = bookInfoService.getBooks();

		return allBooks.flatMap(bookInfo -> {

					Mono<List<Review>> monoList = 
								reviewService.getReviews(bookInfo.getBookId()).collectList();

					return monoList.map(review -> new Book(bookInfo, review));
		
			})
			.onErrorMap(t -> {
			logger.error("Error in getAllBooks");
			return new BookException("Exception while fetching book with retry");
			})
			.retryWhen(retrySpec)
			.log();

	}

	public Mono<Book> getBookById(long bookId) {

		var book = bookInfoService.getBookById(bookId);
		var review = reviewService.getReviews(bookId).collectList();

		return book.zipWith(review, (b, r) -> new Book(b, r));

	}

}
