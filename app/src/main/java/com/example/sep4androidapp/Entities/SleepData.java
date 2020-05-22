package com.example.sep4androidapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class SleepData extends SleepSession {
    private List<RoomCondition> roomConditions;

    public SleepData(List roomConditions, int sleepId, String deviceId, LocalDateTime timeStart, LocalDateTime timeFinish, int rating, double averageCo2, double averageHumidity, double averageSound, double averageTemperature) {
        super(sleepId, deviceId, timeStart, timeFinish, rating, averageCo2, averageHumidity, averageSound, averageTemperature);
        this.roomConditions = roomConditions;

    }


}
