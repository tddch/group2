package com.example.mvpbaaaaaaa.mvp.model.bean;

import com.baidu.mapapi.model.LatLng;

import java.io.Serializable;

public class Lat implements Serializable {
    private LatLng latLng;

    public Lat(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
