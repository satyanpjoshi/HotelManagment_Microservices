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
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
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
                .hotel(getHotel(booking.getHotelId()))
                .customer(getCustomer(booking.getCustomerId()))
                .build();
        return bookingResponse;
    }

    private User getCustomer(Long customerId) {
        Optional<User> customer = userService.getUser(customerId);
        if (customer.isEmpty())
            throw new ResourceNotFoundException("Customer not found for ID: "+ customerId);
        return customer.get();
    }

    private Hotel getHotel(Long hotelId) {
        Optional<Hotel> hotel = hotelService.getHotel(hotelId);
        if(hotel.isEmpty())
            throw new ResourceNotFoundException("Hotel not found for ID: "+ hotelId);
        return hotel.get();
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

    @Override
    public List<BookingResponseModel> getBookingsByHotelId(Long hotelId) {
        List<Booking> bookingList = bookingRepository.findByHotelId(hotelId);

        return bookingList.stream().map(booking ->
                     BookingResponseModel.builder()
                            .id(booking.getId())
                            .bookingDate(booking.getBookingDate())
                            .numberOfDays(booking.getNumberOfDays())
                            .hotel(getHotel(booking.getHotelId()))
                            .customer(getCustomer(booking.getCustomerId()))
                            .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseModel> getBookingsByCustomerId(Long customerId) {
        List<Booking> bookingList = bookingRepository.findByCustomerId(customerId);

        return bookingList.stream().map(booking ->
                        BookingResponseModel.builder()
                                .id(booking.getId())
                                .bookingDate(booking.getBookingDate())
                                .numberOfDays(booking.getNumberOfDays())
                                .hotel(getHotel(booking.getHotelId()))
                                .customer(getCustomer(booking.getCustomerId()))
                                .build()
                )
                .collect(Collectors.toList());
    }
}
