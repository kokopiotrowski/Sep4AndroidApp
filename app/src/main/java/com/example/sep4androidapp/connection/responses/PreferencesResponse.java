package com.example.sep4androidapp.connection.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PreferencesResponse implements Serializable {
    @SerializedName("deviceId")
    private int deviceId;
    @SerializedName("regulationEnabled")
    private boolean regulationEnabled;
    @SerializedName("co2Max")
    private int co2Max;
    @SerializedName("humidityMax")
    private int co2Min;
    @SerializedName("humidityMin")
    private int humidityMax;
    @SerializedName("humidityMin")
    private int humidityMin;
    @SerializedName("temperatureMin")
    private double temperatureMin;
    @SerializedName("temperatureMax")
    private double temperatureMax;

    public PreferencesResponse(int deviceId, boolean regulationEnabled, int co2Max, int humidityMax, int humidityMin, double temperatureMin, double temperatureMax) {
        this.deviceId = deviceId;
        this.regulationEnabled = regulationEnabled;
        this.co2Max = co2Max;
        this.humidityMax = humidityMax;
        this.humidityMin = humidityMin;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isRegulationEnabled() {
        return regulationEnabled;
    }

    public void setRegulationEnabled(boolean regulationEnabled) {
        this.regulationEnabled = regulationEnabled;
    }

    public int getCo2Max() {
        return co2Max;
    }

    public void setCo2Max(int co2Max) {
        this.co2Max = co2Max;
    }

    public int getCo2Min() {
        return co2Min;
    }

    public void setCo2Min(int co2Max) {
        this.co2Max = co2Min;
    }

    public int getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(int humidityMax) {
        this.humidityMax = humidityMax;
    }

    public int getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(int humidityMin) {
        this.humidityMin = humidityMin;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }


}
