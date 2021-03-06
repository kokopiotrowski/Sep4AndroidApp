package com.example.sep4androidapp.Entities;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;

public class SleepSession implements Comparable<SleepSession>{
    @SerializedName("sleepId")
    private int sleepId;
    @SerializedName("deviceId")
    private String deviceId;
    @SerializedName("timeStart")
    private LocalDateTime timeStart;
    @SerializedName("timeFinish")
    private LocalDateTime timeFinish;
    @SerializedName("rating")
    private int rating;
    @SerializedName("averageCo2")
    private double averageCo2;
    @SerializedName("averageHumidity")
    private double averageHumidity;
    @SerializedName("averageSound")
    private double averageSound;
    @SerializedName("averageTemperature")
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

    @Override
    public int compareTo(SleepSession o) {
        return getTimeStart().compareTo(o.getTimeStart());
    }
}
