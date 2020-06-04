package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.Preferences;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PreferencesResponse implements Serializable {
    @SerializedName("deviceId")
    private String deviceId;
    @SerializedName("regulationEnabled")
    private boolean regulationEnabled;
    @SerializedName("co2Max")
    private int co2Max;
    @SerializedName("co2Min")
    private int co2Min;
    @SerializedName("humidityMax")
    private int humidityMax;
    @SerializedName("humidityMin")
    private int humidityMin;
    @SerializedName("temperatureMin")
    private double temperatureMin;
    @SerializedName("temperatureMax")
    private double temperatureMax;

    public PreferencesResponse(String deviceId, boolean regulationEnabled, int co2Max, int co2Min, int humidityMax, int humidityMin, double temperatureMin, double temperatureMax) {
        this.deviceId = deviceId;
        this.regulationEnabled = regulationEnabled;
        this.co2Max = co2Max;
        this.co2Min = co2Min;
        this.humidityMax = humidityMax;
        this.humidityMin = humidityMin;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public boolean isRegulationEnabled() {
        return regulationEnabled;
    }

    public int getCo2Max() {
        return co2Max;
    }

    public int getCo2Min() {
        return co2Min;
    }

    public int getHumidityMax() {
        return humidityMax;
    }

    public int getHumidityMin() {
        return humidityMin;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }
}
