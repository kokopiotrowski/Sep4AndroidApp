package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.SleepSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SleepSessionResponse {


    private int sleepId;
    private String deviceId;
    private LocalDateTime timeStart;
    private LocalDateTime timeFinish;
    private int rating;
    private double averageCo2;
    private double averageHumidity;
    private double averageSound;
    private double averageTemperature;


    public SleepSession getSleepSession(){
        return new SleepSession(sleepId, deviceId, timeStart,timeFinish, rating,averageCo2, averageHumidity, averageSound, averageTemperature);

    }
}
