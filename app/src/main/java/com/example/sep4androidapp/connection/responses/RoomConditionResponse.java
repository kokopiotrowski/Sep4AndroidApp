package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class RoomConditionResponse {
    @SerializedName("sleepId")
    private int sleepId;
    @SerializedName("timestamp")
    private LocalDateTime timestamp;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("co2")
    private double co2;
    @SerializedName("sound")
    private double sound;
    @SerializedName("humidity")
    private double humidity;


    public RoomCondition getRoomCondition(){
        return new RoomCondition(sleepId,timestamp,temperature,co2,sound,humidity);

    }

}
