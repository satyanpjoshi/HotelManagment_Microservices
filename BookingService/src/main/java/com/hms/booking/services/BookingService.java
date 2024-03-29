package com.hms.booking.services;

import com.hms.booking.entites.Booking;
import com.hms.booking.model.BookingResponseModel;

import java.util.List;

public interface BookingService {

    //create

    Booking create(Booking booking);

    //get all
    List<Booking> getAll();

    //get single
    BookingResponseModel get(Long id);

    Booking update(Booking booking);

    void delete(Long bookingId);

    List<BookingResponseModel> getBookingsByHotelId(Long hotelId);

    List<BookingResponseModel> getBookingsByCustomerId(Long customerId);
}
