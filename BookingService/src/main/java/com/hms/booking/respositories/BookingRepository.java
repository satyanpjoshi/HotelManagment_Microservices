package com.hms.booking.respositories;

import com.hms.booking.entites.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
