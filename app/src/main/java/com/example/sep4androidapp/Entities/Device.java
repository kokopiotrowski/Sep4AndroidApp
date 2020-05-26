package com.example.sep4androidapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Device implements Serializable {
    @SerializedName("devideId")
    private String deviceId;
    @SerializedName("name")
    private String name;
    @SerializedName("userId")
    private String userId;

    public Device(String deviceId, String name, String userId) {
        this.deviceId = deviceId;
        this.name = name;
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
