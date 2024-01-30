package com.hms.booking.externalservices;

import com.hms.booking.model.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Optional<Hotel> getHotel(@PathVariable("hotelId") Long hotelId);

    @PostMapping("/hotels/bookRoom/{hotelId}")
    void bookRoom(@PathVariable("hotelId") Long hotelId);

    @PostMapping("/hotels/checkAvailability/{hotelId}")
    Boolean checkAvailability(@PathVariable("hotelId") Long hotelId);

   
}


