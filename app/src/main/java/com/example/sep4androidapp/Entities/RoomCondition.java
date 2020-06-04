package com.example.sep4androidapp.Entities;

import java.time.LocalDateTime;

public class RoomCondition implements Comparable<RoomCondition> {
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getCo2() {
        return co2;
    }

    public double getSound() {
        return sound;
    }

    public double getHumidity() {
        return humidity;
    }

    @Override
    public int compareTo(RoomCondition o) {
        return getTimestamp().compareTo(o.getTimestamp());
    }
}
