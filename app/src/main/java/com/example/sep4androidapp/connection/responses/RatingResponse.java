package com.example.sep4androidapp.connection.responses;

import com.google.gson.annotations.SerializedName;

public class RatingResponse {
    @SerializedName("dateTimeFinish")
    String dateTimeFinish;
    @SerializedName("dateTimeStart")
    String dateTimeStart;
    @SerializedName("deviceId")
    String deviceId;
    @SerializedName("rating")
    int rating;
    @SerializedName("sleepId")
    int sleepId;

    public String getDateTimeFinish() {
        return dateTimeFinish;
    }

    public String getDateTimeStart() {
        return dateTimeStart;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getRating() {
        return rating;
    }

    public int getSleepId() {
        return sleepId;
    }
}
