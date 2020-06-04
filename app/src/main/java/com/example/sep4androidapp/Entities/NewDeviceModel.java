package com.example.sep4androidapp.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "newDevice_table")
public class NewDeviceModel {

    @PrimaryKey
    @NonNull
    private String deviceId;
    private String name;

    public NewDeviceModel(String deviceId, String name) {
        this.deviceId = deviceId;
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }
}
