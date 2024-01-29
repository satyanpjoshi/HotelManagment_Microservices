package com.hms.hotel.services.impl;

import com.hms.hotel.entites.Hotel;
import com.hms.hotel.exceptions.ResourceNotFoundException;
import com.hms.hotel.respositories.HotelRepository;
import com.hms.hotel.services.HotelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
    }

    @Override
    public Hotel update(Hotel hotel) {
        Optional<Hotel> existingHotelOpt = hotelRepository.findById(hotel.getId());
        if(existingHotelOpt.isEmpty())
            throw new ResourceNotFoundException("Hotel not found for given ID: "+ hotel.getId());

        Hotel existingHotel = existingHotelOpt.get();
        BeanUtils.copyProperties(hotel, existingHotel, "id");
        return hotelRepository.save(existingHotel);
    }

    @Override
    public void delete(Long hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
