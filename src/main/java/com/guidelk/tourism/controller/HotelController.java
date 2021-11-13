package com.guidelk.tourism.controller;

import com.guidelk.tourism.service.HotelService;
import com.guidelk.tourism.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("hotels")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/getMasterStatusList")
    @PreAuthorize("hasRole('ROLE_operationalInfo@hotel_VIEW')")
    public List<MasterDataStatus> findStatusList(@RequestParam("filter") String filter) {
        return MasterDataStatus.getMasterStatusActionWise(filter);
    }
}
