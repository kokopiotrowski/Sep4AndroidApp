package com.example.sep4androidapp.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SleepData extends SleepSession {
    private ArrayList<RoomCondition> roomConditions;

    public SleepData(ArrayList roomConditions, int sleepId, String deviceId, LocalDateTime timeStart, LocalDateTime timeFinish, int rating, double averageCo2, double averageHumidity, double averageSound, double averageTemperature) {
        super(sleepId, deviceId, timeStart, timeFinish, rating, averageCo2, averageHumidity, averageSound, averageTemperature);
        this.roomConditions = roomConditions;
    }

    public ArrayList<RoomCondition> getRoomConditions(){

        return roomConditions;
    }




}
