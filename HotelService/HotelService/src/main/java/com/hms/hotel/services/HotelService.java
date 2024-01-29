package com.hms.hotel.services;

import com.hms.hotel.entites.Hotel;

import java.util.List;

public interface HotelService {

    //create

    Hotel create(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Hotel get(String id);

    Hotel update(Hotel hotel);

    void delete(String hotelId);
}
