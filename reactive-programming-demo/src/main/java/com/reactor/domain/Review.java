package com.reactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	
	
	private long reviewId;
	private long bookId;
	private double ratings;
	private String comments;
	
	public Review(long reviewId, long bookId, double ratings, String comments) {
		super();
		this.reviewId = reviewId;
		this.bookId = bookId;
		this.ratings = ratings;
		this.comments = comments;
	}
	
	
	

}
