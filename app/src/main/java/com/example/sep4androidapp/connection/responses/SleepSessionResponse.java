package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.SleepSession;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SleepSessionResponse implements Serializable {
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


    public SleepSession getSleepSession(){
        return new SleepSession(sleepId, deviceId, timeStart,timeFinish, rating,averageCo2, averageHumidity, averageSound, averageTemperature);

    }
}
