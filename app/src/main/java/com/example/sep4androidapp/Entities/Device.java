package com.example.sep4androidapp.Entities;

import java.io.Serializable;

public class Device implements Serializable {
    private String deviceId;
    private String name;
    private String userId;

    public Device(String deviceId, String name, String userId) {
        this.deviceId = deviceId;
        this.name = name;
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
