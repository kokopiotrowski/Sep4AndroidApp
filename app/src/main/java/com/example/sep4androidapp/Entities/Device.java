package com.example.sep4androidapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Device implements Serializable {
    private String deviceId;
    private String name;

    public Device(String deviceId, String name) {
        this.deviceId = deviceId;
        this.name = name;
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
}
