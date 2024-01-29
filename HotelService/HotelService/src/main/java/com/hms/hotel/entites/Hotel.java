package com.hms.hotel.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @Column(name = "id",nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String contactNumber;
    private Integer numberOfRoomsAvailable;
    private  String location;
    private  String rating;


}
