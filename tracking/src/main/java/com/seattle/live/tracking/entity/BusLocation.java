package com.seattle.live.tracking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "bus_locations")
public class BusLocation {

    @JsonProperty("vehicleId")
    @Id
    private  Long vehicleId;
    @JsonProperty("routeTag")
    private String routeTag;
    @JsonProperty("dirTag")
    private  String dirTag;
    @JsonProperty("latitude")
    private  Double latitude;
    @JsonProperty("longitude")
    private  Double longitude;
    @JsonProperty("secsSinceReport")
    private  Integer secsSinceReport;
    @JsonProperty("predictable")
    private  Boolean predictable;
    @JsonProperty("heading")
    private  Integer heading;
    @JsonProperty("speedKmHr")
    private  Double speedKmHr;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private  LocalDateTime createdAt;

    public BusLocation(
            Long vehicleId,
            String routeTag,
            String dirTag,
            Double latitude,
            Double longitude,
            Integer secsSinceReport,
            Boolean predictable,
            Integer heading,
            Double speedKmHr,
            LocalDateTime createdAt
    ) {
        this.vehicleId = vehicleId;
        this.routeTag = routeTag;
        this.dirTag = dirTag;
        this.latitude = latitude;
        this.longitude = longitude;
        this.secsSinceReport = secsSinceReport;
        this.predictable = predictable;
        this.heading = heading;
        this.speedKmHr = speedKmHr;
        this.createdAt = createdAt;
    }

    public BusLocation() {

    }

    public String routeTag() {
        return routeTag;
    }

    public String dirTag() {
        return dirTag;
    }

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }

    public Integer secsSinceReport() {
        return secsSinceReport;
    }

    public Boolean predictable() {
        return predictable;
    }

    public Integer heading() {
        return heading;
    }

    public Double speedKmHr() {
        return speedKmHr;
    }

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    public LocalDateTime createdAt() {
        return createdAt;
    }


    @Override
    public String toString() {
        return "BusLocation[" +
                "vehicleId=" + vehicleId + ", " +
                "routeTag=" + routeTag + ", " +
                "dirTag=" + dirTag + ", " +
                "latitude=" + latitude + ", " +
                "longitude=" + longitude + ", " +
                "secsSinceReport=" + secsSinceReport + ", " +
                "predictable=" + predictable + ", " +
                "heading=" + heading + ", " +
                "speedKmHr=" + speedKmHr + ", " +
                "createdAt=" + createdAt + ']';
    }
}