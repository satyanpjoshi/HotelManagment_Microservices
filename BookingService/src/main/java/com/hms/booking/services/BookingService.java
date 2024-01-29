package com.hms.booking.services;

import com.hms.booking.entites.Booking;

import java.util.List;

public interface BookingService {

    //create

    Booking create(Booking booking);

    //get all
    List<Booking> getAll();

    //get single
    Booking get(Long id);

    Booking update(Booking booking);

    void delete(Long bookingId);
}
