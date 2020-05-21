package com.example.sep4androidapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class RoomCondition implements Serializable {
    @SerializedName("sleepId")
    private int sleepId;
    @SerializedName("timeStamp")
    private LocalDateTime timeStamp;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("co2")
    private double co2;
    @SerializedName("sound")
    private double sound;
    @SerializedName("humidity")
    private double humidity;


    public RoomCondition(int sleepId, LocalDateTime timeStamp, double temperature, double co2, double sound, double humidity) {
        this.sleepId = sleepId;
        this.timeStamp = timeStamp;
        this.temperature = temperature;
        this.co2 = co2;
        this.sound = sound;
        this.humidity = humidity;

    }

    public int getSleepId() {
        return sleepId;
    }

    public void setSleepId(int sleepId) {
        this.sleepId = sleepId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getSound() {
        return sound;
    }

    public void setSound(double sound) {
        this.sound = sound;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
