package com.seattle.live.tracking.controller;


import com.seattle.live.tracking.entity.BusLocation;
import com.seattle.live.tracking.service.BusLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bus-live")
public class BusLocationController {

    Logger LOGGER  = LoggerFactory.getLogger(BusLocationController.class);
    private final BusLocationService busLocationService;

    @Autowired
    public BusLocationController(BusLocationService busLocationService) {
        this.busLocationService = busLocationService;
    }

    @GetMapping("/geoLocation")
    @ResponseBody
    public List<BusLocation> getBusLocations() {
        return busLocationService.getBusLocations();
    }
}
