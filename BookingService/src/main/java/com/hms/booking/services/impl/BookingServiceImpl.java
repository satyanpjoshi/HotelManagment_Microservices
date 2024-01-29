package com.hms.booking.services.impl;

import com.hms.booking.entites.Booking;
import com.hms.booking.exceptions.ResourceNotFoundException;
import com.hms.booking.externalservices.HotelService;
import com.hms.booking.externalservices.UserService;
import com.hms.booking.model.BookingResponseModel;
import com.hms.booking.model.Hotel;
import com.hms.booking.model.User;
import com.hms.booking.respositories.BookingRepository;
import com.hms.booking.services.BookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelService hotelService;

    private UserService userService;

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingResponseModel get(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if(bookingOptional.isEmpty())
            throw new ResourceNotFoundException("Booking not found for ID: "+id);
        Booking booking = bookingOptional.get();
        BookingResponseModel bookingResponse = BookingResponseModel.builder()
                .id(booking.getId())
                .bookingDate(booking.getBookingDate())
                .numberOfDays(booking.getNumberOfDays())
                .build();
        Optional<Hotel> hotel = hotelService.getHotel(booking.getHotelId());
        if(hotel.isEmpty())
            throw new ResourceNotFoundException("Hotel not found for ID: "+ booking.getHotelId());
        bookingResponse.setHotel(hotel.get());

        Optional<User> customer = userService.getUser(booking.getCustomerId());
        if (customer.isEmpty())
            throw new ResourceNotFoundException("Customer not found for ID: "+booking.getCustomerId());
        bookingResponse.setCustomer(customer.get());
        return bookingResponse;
    }

    @Override
    public Booking update(Booking booking) {
        Optional<Booking> existingBookingOpt = bookingRepository.findById(booking.getId());
        if(existingBookingOpt.isEmpty())
            throw new ResourceNotFoundException("Booking not found for given ID: "+ booking.getId());

        Booking existingBooking = existingBookingOpt.get();
        BeanUtils.copyProperties(booking, existingBooking, "id");
        return bookingRepository.save(existingBooking);
    }

    @Override
    public void delete(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
