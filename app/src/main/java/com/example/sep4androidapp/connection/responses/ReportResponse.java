package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.SleepSession;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReportResponse implements Serializable {

    @SerializedName("sleepSessions")
    private List<SleepSession> sleepSessions;

    public List<SleepSession> getSleepSessions() {
        return sleepSessions;
    }
}
