package com.example.sep4androidapp.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;


public class RoomCondition implements Serializable {
    private int sleepId;
    private LocalDateTime timestamp;
    private double temperature;
    private double co2;
    private double sound;
    private double humidity;

    public RoomCondition(int sleepId, LocalDateTime timestamp, double temperature, double co2, double sound, double humidity) {
        this.sleepId = sleepId;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
