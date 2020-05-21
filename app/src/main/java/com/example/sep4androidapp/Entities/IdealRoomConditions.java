package com.example.sep4androidapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IdealRoomConditions implements Serializable {
    @SerializedName("maxCo2")
    private int maxCo2;
    @SerializedName("minHumidity")
    private double minHumidity;
    @SerializedName("maxHumidity")
    private double maxHumidity;
    @SerializedName("minTemperature")
    private double minTemperature;
    @SerializedName("maxTemperature")
    private double maxTemperature;

    public IdealRoomConditions(int maxCo2, double minHumidity, double maxHumidity, double minTemperature, double maxTemperature) {
        this.maxCo2 = maxCo2;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public int getMaxCo2() {
        return maxCo2;
    }

    public void setMaxCo2(int maxCo2) {
        this.maxCo2 = maxCo2;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(double minHumidity) {
        this.minHumidity = minHumidity;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
