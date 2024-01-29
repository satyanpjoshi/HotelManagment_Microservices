package com.hms.booking.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BookingResponseModel {
    private Long id;
    private Integer numberOfDays;
    private Date bookingDate;
    private Hotel hotel;
    private User customer;
}
