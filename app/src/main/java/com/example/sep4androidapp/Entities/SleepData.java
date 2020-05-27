package com.example.sep4androidapp.Entities;

import androidx.room.Room;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SleepData extends SleepSession {
    private ArrayList<RoomCondition> roomConditions;

    public SleepData(ArrayList roomConditions, int sleepId, String deviceId, LocalDateTime timeStart, LocalDateTime timeFinish, int rating, double averageCo2, double averageHumidity, double averageSound, double averageTemperature) {
        super(sleepId, deviceId, timeStart, timeFinish, rating, averageCo2, averageHumidity, averageSound, averageTemperature);
        this.roomConditions = roomConditions;

    }

    public RoomCondition getRoomCondition(int index){

        return roomConditions.get(index);
    }
    public ArrayList<RoomCondition> getRoomConditions(){

        return roomConditions;
    }




}
