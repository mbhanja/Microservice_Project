package com.lcwd.user.service;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Test
	void createRatingTest() {
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is using Feign client").build();
		ResponseEntity<Rating> responseEntity = ratingService.createRating(rating);
		responseEntity.getStatusCode();
		System.out.println("New Rating Created");
	}

}
