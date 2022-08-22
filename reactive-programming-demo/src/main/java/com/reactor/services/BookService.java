package com.reactor.services;

import java.util.List;

import com.reactor.domain.Book;
import com.reactor.domain.Review;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class BookService {

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

			Mono<List<Review>> monoList = reviewService.getReviews(bookInfo.getBookId()).collectList();

			return monoList.map(review -> new Book(bookInfo, review));
		}).log();

	}

}
