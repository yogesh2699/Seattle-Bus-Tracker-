package com.seattle.live.tracking.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "route")
public class Route {

    public Route() {
    }

    public Route(Long id, String routeTag, String title) {
        this.id = id;
        this.routeTag = routeTag;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("routeTag")
    private  String routeTag;
    @JsonProperty("title")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public void setRouteTag(String routeTag) {
        this.routeTag = routeTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
