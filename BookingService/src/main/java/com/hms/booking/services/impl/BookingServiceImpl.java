package com.hms.booking.services.impl;

import com.hms.booking.entites.Booking;
import com.hms.booking.exceptions.ResourceNotFoundException;
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

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking get(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("booking with given id not found !!"));
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
