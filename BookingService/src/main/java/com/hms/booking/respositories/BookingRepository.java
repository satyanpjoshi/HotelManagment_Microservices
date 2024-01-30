package com.hms.booking.respositories;

import com.hms.booking.entites.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByHotelId(Long hotelId);

    List<Booking> findByCustomerId(Long customerId);
}
