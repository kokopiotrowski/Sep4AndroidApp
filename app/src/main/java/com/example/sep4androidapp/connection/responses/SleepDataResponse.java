package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.SleepData;
import com.example.sep4androidapp.Entities.SleepSession;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class SleepDataResponse {
    @SerializedName("roomConditions")
    private List<RoomCondition> roomConditions;
    private int sleepId;
    private String deviceId;
    private LocalDateTime timeStart;
    private LocalDateTime timeFinish;
    private int rating;
    private double averageCo2;
    private double averageHumidity;
    private double averageSound;
    private double averageTemperature;

        public SleepData getSleepData() {
        return new SleepData(roomConditions,sleepId, deviceId, timeStart, timeFinish, rating, averageCo2, averageHumidity, averageSound,  averageTemperature);
    }
}
