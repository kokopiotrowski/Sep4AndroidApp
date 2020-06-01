package com.example.sep4androidapp.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "preferences_table")
public class Preferences {

    @PrimaryKey
    @NonNull
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

//    public Preferences(int co2Max,int co2Min, int humidityMax, int humidityMin, double temperatureMin, double temperatureMax){
//        this.co2Max = co2Max;
//        this.co2Min = co2Min;
//        this.humidityMax = humidityMax;
//        this.humidityMin = humidityMin;
//        this.temperatureMin = temperatureMin;
//        this.temperatureMax = temperatureMax;
//    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
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

    public void setCo2Min(int co2Min) {
        this.co2Min = co2Min;
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
