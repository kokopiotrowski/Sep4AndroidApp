package com.example.sep4androidapp.connection.responses;


import com.example.sep4androidapp.Entities.StartStop;

import java.time.LocalDateTime;

public class StartStopResponse {

    private LocalDateTime dateTimeFinish;

    private LocalDateTime dateTimeStart;

    private String deviceId;

    private double rating;

    private double sleepId;

    public StartStop getStartStop() {
        return new StartStop(dateTimeFinish, dateTimeStart, deviceId, rating, sleepId);
    }
}
