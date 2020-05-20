package com.example.sep4androidapp.connection.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeviceResponse implements Serializable {
    @SerializedName("deviceId")
    private int deviceId;

    @SerializedName("name")
    private String name;

    public DeviceResponse(int deviceId, String name) {
        this.deviceId = deviceId;
        this.name = name;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
