package com.lcwd.hotel.service.services;

import com.lcwd.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create

     Hotel saveHotel(Hotel hotel);

     List<Hotel> getAllHotels();

     Hotel getHotel(String id);

     Hotel deleteHotel(String id);
}
