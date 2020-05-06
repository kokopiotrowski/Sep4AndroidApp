package com.example.sep4androidapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "preferences_table")
public class Preferences {

    @PrimaryKey(autoGenerate = true)
    private int deviceId;
    private boolean regulationEnabled;
    private int co2Max;
    private int humidityMax;
    private int humidityMin;
    private double temperatureMin;
    private double temperatureMax;

    public Preferences(int deviceId, boolean regulationEnabled, int co2Max, int humidityMax, int humidityMin, double temperatureMin, double temperatureMax) {
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
