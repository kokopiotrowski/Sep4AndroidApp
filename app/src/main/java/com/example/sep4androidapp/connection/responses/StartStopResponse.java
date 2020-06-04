package com.example.sep4androidapp.connection.responses;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class StartStopResponse {

    @SerializedName("dateTimeFinish")
    private LocalDateTime dateTimeFinish;

    @SerializedName("dateTimeStart")
    private LocalDateTime dateTimeStart;

    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("rating")
    private double rating;

    @SerializedName("sleepId")
    private double sleepId;
}
