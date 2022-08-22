package com.reactor.services;

import java.util.List;

import com.reactor.domain.Review;

import reactor.core.publisher.Flux;

public class ReviewService {

	public Flux<Review> getReviews(long bookId) {

		var reviewList = List.of(

				new Review(1, bookId, 9.1, "Goog to Read"), new Review(2, bookId, 8.6, "Awesome Reading"));

		return 	Flux.fromIterable(reviewList);	
	}

}
