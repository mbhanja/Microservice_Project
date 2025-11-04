package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RatingService")
@Service
public interface RatingService {

    //POST method
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating rating);

    //PUT method
    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String RatingId, Rating rating);

    //DELETE method
    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable("ratingID") String ratingId);
}
