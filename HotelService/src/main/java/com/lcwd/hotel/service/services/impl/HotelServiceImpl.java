package com.lcwd.hotel.service.services.impl;

import com.lcwd.hotel.service.entities.Hotel;
import com.lcwd.hotel.service.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.service.repositories.HotelRepository;
import com.lcwd.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString(); // Generate a random UUID for the hotel ID
        hotel.setId(randomHotelId); // Set the generated ID to the hotel object
        return  hotelRepository.save(hotel);
    }

    @Override
    public Hotel deleteHotel(String id) {
        return hotelRepository.findById(id).map(hotel -> {;
            hotelRepository.delete(hotel);
            return hotel;
        }).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Hotel not found with id: " + id));

    }
}
