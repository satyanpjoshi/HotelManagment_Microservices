package com.hms.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private Long id;
    private String contactNumber;
    private Integer numberOfRoomsAvailable;
    private String location;
    private String rating;

}
