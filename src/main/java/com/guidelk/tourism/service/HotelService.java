package com.guidelk.tourism.service;

import com.guidelk.tourism.entity.Hotel;
import com.guidelk.tourism.vo.HotelVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HotelService {
    ResponseEntity createHotel(Hotel hotel);

    ResponseEntity<Hotel> updateHotel(Hotel hotel);

    ResponseEntity<Hotel> deleteHotel(Integer hotelId);

    List<Hotel> hotelSearch(HotelVo hotelVo);
}
