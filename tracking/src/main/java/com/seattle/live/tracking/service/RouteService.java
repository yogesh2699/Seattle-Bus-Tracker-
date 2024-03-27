package com.seattle.live.tracking.service;

import com.seattle.live.tracking.entity.Route;
import com.seattle.live.tracking.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    Logger LOGGER  = LoggerFactory.getLogger(RouteService.class);

//    private final BusLocationRepository busLocationRepository;
//
//    @Autowired
//    public BusLocationService(BusLocationRepository busLocationRepository) {
//        this.busLocationRepository = busLocationRepository;
//    }
//
//    public List<BusLocation> getBusLocations(){
//        String apiUrl = "https://retro.umoiq.com/service/publicXMLFeed?command=vehicleLocations&a=ttc";
//        String xmlData = fetchXmlData(apiUrl);
//        return parseBusLocations(xmlData);
//    }

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }

    public List<Route> getRoutes(){
        return null;
    }
}
