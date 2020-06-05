package com.example.sep4androidapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "preferences_table")
public class Preferences {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String deviceId;
    private boolean regulationEnabled;
    private int co2Max;
    private int co2Min;
    private int humidityMax;
    private int humidityMin;
    private double temperatureMin;
    private double temperatureMax;

    public Preferences(String deviceId, boolean regulationEnabled, int co2Max, int co2Min, int humidityMax, int humidityMin, double temperatureMin, double temperatureMax) {
        this.deviceId = deviceId;
        this.regulationEnabled = regulationEnabled;
        this.co2Max = co2Max;
        this.co2Min = co2Min;
        this.humidityMax = humidityMax;
        this.humidityMin = humidityMin;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
