package com.hms.hotel.controllers;

import com.hms.hotel.entites.Hotel;
import com.hms.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }


    //get single
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }


    //get all
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(hotelService.getAll());
    }

    @PutMapping
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.update(hotel));
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<?> deleteHotelById(@PathVariable Long hotelId) {
        hotelService.delete(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/checkAvailability/{hotelId}")
    public boolean checkAvailability(@PathVariable Long hotelId) {
        return hotelService.areRoomsAvailable(hotelId);
    }

    @PostMapping("/bookRoom/{hotelId}")
    public void bookRoom(@PathVariable Long hotelId) {
        hotelService.bookRoom(hotelId);
    }
}
