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
}
