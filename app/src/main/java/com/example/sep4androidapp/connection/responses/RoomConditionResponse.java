package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.RoomCondition;

import java.time.LocalDateTime;
import java.util.Date;

public class RoomConditionResponse {
    private int sleepId;
    private LocalDateTime timeStamp;
    private double temperature;
    private double co2;
    private double sound;
    private double humidity;


    public RoomCondition getRoomCondition(){
        return new RoomCondition(sleepId,timeStamp,temperature,co2,sound,humidity);

    }

}
