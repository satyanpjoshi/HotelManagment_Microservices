package com.hms.hotel.respositories;

import com.hms.hotel.entites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Modifying
    @Query("UPDATE Hotel h SET h.numberOfRoomsAvailable = h.numberOfRoomsAvailable - 1 WHERE h.id = :hotelId " +
            "AND h.numberOfRoomsAvailable > 0")
    int bookRoom(@Param("hotelId") Long hotelId);
}
