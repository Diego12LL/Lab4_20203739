package com.example.lab4_20203739;

public class LocationInfo {
    private String cityName;
    private double latitude;
    private double longitude;

    public LocationInfo(String cityName, double latitude, double longitude) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}