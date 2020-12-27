package com.example.mvpbaaaaaaa.mvp.model.bean;

import com.baidu.mapapi.model.LatLng;

import java.io.Serializable;

public class Lat implements Serializable {
    private LatLng latLng;
    private String city;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Lat(LatLng latLng, String city) {
        this.latLng = latLng;
        this.city = city;
    }
}
