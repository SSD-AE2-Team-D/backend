package com.guidelk.tourism.serviceimpl;

import com.guidelk.tourism.repository.HotelRepository;
import com.guidelk.tourism.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);


    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
}
