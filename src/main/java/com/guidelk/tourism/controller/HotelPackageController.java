package com.guidelk.tourism.controller;


import com.guidelk.tourism.entity.HotelPackage;
import com.guidelk.tourism.service.HotelPackageService;
import com.guidelk.tourism.util.HotelPackageActivityType;
import com.guidelk.tourism.util.MasterDataStatus;
import com.guidelk.tourism.vo.HotelPackageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("packages")
public class HotelPackageController {
    private final HotelPackageService hotelPackageService;

    @Autowired
    public HotelPackageController(HotelPackageService hotelPackageService) {
        this.hotelPackageService = hotelPackageService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_operationalInfo@package_CREATE')")
    public ResponseEntity createHotelPackage(@Valid @RequestBody HotelPackage hotelPackage) {
        return this.hotelPackageService.createHotelPackage(hotelPackage);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_operationalInfo@package_UPDATE')")
    public ResponseEntity<HotelPackage> updateHotelPackage(@Valid @RequestBody HotelPackage hotelPackage) {
        return this.hotelPackageService.updateHotelPackage(hotelPackage);
    }

    @DeleteMapping("{hotelPackageId}")
    @PreAuthorize("hasRole('ROLE_operationalInfo@package_DELETE')")
    public ResponseEntity<HotelPackage> deleteHotelPackage(@PathVariable("hotelPackageId") Integer hotelPackageId) {
        return this.hotelPackageService.deleteHotelPackage(hotelPackageId);
    }

    @GetMapping("/getHotelActivityTypeList")
    @PreAuthorize("hasRole('ROLE_operationalInfo@package_VIEW')")
    public List<HotelPackageActivityType> getHotelActivityTypeList() {
        return Arrays.asList(HotelPackageActivityType.values());
    }

    @PostMapping("/hotelPackageSearch")
    @PreAuthorize("hasRole('ROLE_operationalInfo@package_VIEW')")
    public List<HotelPackage> hotelPackageSearch(@RequestBody HotelPackageVo hotelPackageVo) {
        return this.hotelPackageService.hotelPackageSearch(hotelPackageVo);
    }

    @GetMapping("/getMasterStatusList")
    @PreAuthorize("hasRole('ROLE_operationalInfo@package_VIEW')")
    public List<MasterDataStatus> findStatusList(@RequestParam("filter") String filter) {
        return MasterDataStatus.getMasterStatusActionWise(filter);
    }

}
