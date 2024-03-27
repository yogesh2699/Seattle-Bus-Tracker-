package com.seattle.live.tracking.service;

import com.seattle.live.tracking.entity.BusLocation;
import com.seattle.live.tracking.repository.BusLocationRepository;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusLocationService {

    Logger LOGGER  = LoggerFactory.getLogger(BusLocationService.class);
    private final BusLocationRepository busLocationRepository;

    @Autowired
    public BusLocationService(BusLocationRepository busLocationRepository) {
        this.busLocationRepository = busLocationRepository;
    }

    public List<BusLocation> getBusLocations(){
        String apiUrl = "https://retro.umoiq.com/service/publicXMLFeed?command=vehicleLocations&a=ttc";
        String xmlData = fetchXmlData(apiUrl);
        return parseBusLocations(xmlData);
    }

    public void saveBusLocations(List<BusLocation> busLocations) {
        busLocationRepository.saveAll(busLocations);
    }

    public String fetchXmlData(String apiUrl) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(apiUrl).build();
            return httpclient.execute(httpGet, response -> {
                final HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return result;
            });
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse xml data", e);
        }
    }

    public List<BusLocation> parseBusLocations(String xmlData) {
        Document document = Jsoup.parse(xmlData);
        Elements vehicleElements = document.select("vehicle");

        List<BusLocation> busLocations = new ArrayList<>();

        for (Element vehicleElement : vehicleElements) {
            Long vehicleId = Long.parseLong(vehicleElement.attr("id"));
            String routeTag = vehicleElement.attr("routeTag");
            String dirTag = vehicleElement.attr("dirTag");
            Double latitude = Double.parseDouble(vehicleElement.attr("lat"));
            Double longitude = Double.parseDouble(vehicleElement.attr("lon"));
            Integer secsSinceReport = Integer.parseInt(vehicleElement.attr("secsSinceReport"));
            Boolean predictable = Boolean.parseBoolean(vehicleElement.attr("predictable"));
            Integer heading = Integer.parseInt(vehicleElement.attr("heading"));
            Double speedKmHr = Double.parseDouble(vehicleElement.attr("speedKmHr"));

            BusLocation busLocation = new BusLocation(
                    vehicleId,
                    routeTag,
                    dirTag,
                    latitude,
                    longitude,
                    secsSinceReport,
                    predictable,
                    heading,
                    speedKmHr,
                    null
            );
            LOGGER.info("Bus Location {}", busLocation.toString());
            busLocations.add(busLocation);
        }
        return busLocations;
    }

}
