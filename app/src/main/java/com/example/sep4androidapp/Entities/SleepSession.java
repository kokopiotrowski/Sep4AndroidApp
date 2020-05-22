package com.example.sep4androidapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SleepSession implements Serializable {
    private int sleepId;
    private String deviceId;
    private LocalDateTime timeStart;
    private LocalDateTime timeFinish;
    private int rating;
    private double averageCo2;
    private double averageHumidity;
    private double averageSound;
    private double averageTemperature;


    public SleepSession(int sleepId, String deviceId, LocalDateTime timeStart, LocalDateTime timeFinish, int rating, double averageCo2, double averageHumidity, double averageSound, double averageTemperature) {
        this.sleepId = sleepId;
        this.deviceId = deviceId;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.rating = rating;
        this.averageCo2 = averageCo2;
        this.averageHumidity = averageHumidity;
        this.averageSound = averageSound;
        this.averageTemperature = averageTemperature;

    }

    public int getSleepId() {
        return sleepId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public LocalDateTime getTimeFinish() {
        return timeFinish;
    }

    public int getRating() {
        return rating;
    }

    public double getAverageCo2() {
        return averageCo2;
    }

    public double getAverageHumidity() {
        return averageHumidity;
    }

    public double getAverageSound() {
        return averageSound;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }


}
