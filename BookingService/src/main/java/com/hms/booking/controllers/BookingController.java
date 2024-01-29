package com.hms.booking.controllers;

import com.hms.booking.entites.Booking;
import com.hms.booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //create

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.create(booking));
    }


    //get single
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.get(bookingId));
    }


    //get all
    @GetMapping
    public ResponseEntity<List<Booking>> getAll(){
        return ResponseEntity.ok(bookingService.getAll());
    }

    @PutMapping
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.update(booking));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteBookingById(@PathVariable Long bookingId) {
        bookingService.delete(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
