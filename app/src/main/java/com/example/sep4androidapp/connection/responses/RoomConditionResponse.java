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
    private Integer temperature;
    @SerializedName("co2")
    private Integer co2;
    @SerializedName("sound")
    private Double sound;
    @SerializedName("humidity")
    private Double humidity;

    public RoomCondition getRoomCondition(){
        return new RoomCondition(sleepId,timestamp,
                temperature == null ? 0:temperature,
                co2 == null ? 0:co2,
                sound == null ? 0:sound,
                humidity == null ? 0:humidity);
    }

}
