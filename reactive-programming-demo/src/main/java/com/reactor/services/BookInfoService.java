package com.reactor.services;

import java.util.List;

import com.reactor.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookInfoService {

	public Flux<BookInfo> getBooks() {

		var books = List.of(

				new BookInfo(1, "Book 1", "Author 1", "123123"), new BookInfo(2, "Book 2", "Author 2", "4323232"),
				new BookInfo(3, "Book 3", "Author 3", "11112222"));

		return Flux.fromIterable(books);

	}

	public Mono<BookInfo> getBookById(long bookId) {

		var book = new BookInfo(bookId, "Book 1", "Author 1", "123123");

		return Mono.just(book);

	}

}
