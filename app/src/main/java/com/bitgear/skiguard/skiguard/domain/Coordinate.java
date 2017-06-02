package com.bitgear.skiguard.skiguard.domain;

/**
 * Created by ila on 2.6.17..
 */

public class Coordinate {

    private Float lat;
    private Float lng;


    public Coordinate(Float lat, Float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

}
