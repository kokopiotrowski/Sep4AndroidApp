package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.Device;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeviceResponse implements Serializable {
    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("name")
    private String name;

    @SerializedName("userId")
    private String userId;

    public Device getDevice()
    {
        return new Device(deviceId, name, userId);
    }

}
