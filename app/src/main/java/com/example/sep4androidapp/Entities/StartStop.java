package com.example.sep4androidapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StartStop implements Serializable {
   private LocalDateTime dateTimeFinish;
    private LocalDateTime dateTimeStart;
    private String deviceId;
    private double rating;
    private double sleepId;

    public StartStop(LocalDateTime dateTimeFinish, LocalDateTime dateTimeStart, String deviceId, double rating, double sleepId) {
        this.dateTimeFinish = dateTimeFinish;
        this.dateTimeStart = dateTimeStart;
        this.deviceId = deviceId;
        this.rating = rating;
        this.sleepId = sleepId;
    }

    public LocalDateTime getDateTimeFinish() {
        return dateTimeFinish;
    }

    public void setDateTimeFinish(LocalDateTime dateTimeFinish) {
        this.dateTimeFinish = dateTimeFinish;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getSleepId() {
        return sleepId;
    }

    public void setSleepId(double sleepId) {
        this.sleepId = sleepId;
    }
}
