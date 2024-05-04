package com.example.lab4_20203739;
public class ClimaInfo {
    private String cityName;
    private double temperature;
    private double tempMin;
    private double tempMax;
    private String windDirection;

    public ClimaInfo(String cityName, double temperature, double tempMin, double tempMax, String windDirection) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.windDirection = windDirection;
    }

    // Getters para acceder a los datos del clima
    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public String getWindDirection() {
        return windDirection;
    }
}
