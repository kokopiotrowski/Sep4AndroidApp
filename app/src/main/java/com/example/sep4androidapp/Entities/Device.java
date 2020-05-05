package com.example.sep4androidapp.Entities;

public class Device {
    private int deviceId;
    private String name;

    public Device(int deviceId, String name) {
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
