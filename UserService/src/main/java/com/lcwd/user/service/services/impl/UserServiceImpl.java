package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique user ID
        String randomUserID = UUID.randomUUID().toString();
        user.setUserID(randomUserID);// Generate a random UUID for the user ID
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // Implement RATING SERVICE CALL

       List<User> users = userRepository.findAll();
//      for (User user : users) {
//          Rating[] ratings = restTemplate.getForObject(
//                  "http://localhost:8083/ratings/users/" + user.getUserID(),
//                  Rating[].class
//         );
//
//        List<Rating> ratingList = java.util.Arrays.asList(ratings);
//        user.setRatings(ratingList);
        return users;

    }

    @Override
    public User getUserById(String userId) {
        //get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id : " + userId));

        //fetch ratings of the above user from rating service
        Rating[] ratings = restTemplate.getForObject("http://RatingService/ratings/users/"+user.getUserID() , Rating[].class);
        List<Rating> ratingList = Arrays.stream(ratings).toList();

        List<Rating> ratingList1 = ratingList.stream().map(rating -> {
            //api call to hotel service to get the hotel using RestTemplate
            //http://localhost:8082/hotels/126aadbe-6235-4b3f-b22f-5e0e67d1f174
//            ResponseEntity<Hotel> responseEntity =  restTemplate.getForEntity("http://HotelService/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = responseEntity.getBody();
//            logger.info("Response status code: " + responseEntity.getStatusCode());

            //using Feign client how to call one microservice to another microservice
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        // set the ratings to user
        user.setRatings(ratingList);
        //return the user
        return user;
    }
}
