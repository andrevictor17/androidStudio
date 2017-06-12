package com.example.quiroz.googlemaps2;

/**
 * Created by andre.rosa on 30/05/2017.
 */

public class MarkerPol {

    private Double lat;
    private Double lon;
    private String description;

    public MarkerPol(Double l, Double lo, String desc){
        lat = l;
        lon = lo;
        description = desc;
    }

    public Double getLat(){
        return lat;
    }

    public Double getLon(){
        return lon;
    }

    public String getDesc() { return description; }
}
