package com.godiya.carrent;

/**
 * Created by DUTT PATEL on 11/2/2017.
 */

public class location_model {
    int location_id;
    String location_name;
    String location_address;
    double location_lat;
    double location_lon;

    public location_model(int location_id, String location_name, String location_address, double location_lat, double location_lon) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.location_address = location_address;
        this.location_lat = location_lat;
        this.location_lon = location_lon;
    }

    public location_model() {
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public double getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(double location_lat) {
        this.location_lat = location_lat;
    }

    public double getLocation_lon() {
        return location_lon;
    }

    public void setLocation_lon(double location_lon) {
        this.location_lon = location_lon;
    }
}
