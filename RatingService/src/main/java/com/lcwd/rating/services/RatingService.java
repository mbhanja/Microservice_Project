package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get all rating by user id
    List<Rating> getRatingsByUserId(String userId);

    //get all by hotel id
    List<Rating> getRatingsByHotelId(String hotelId);
}
