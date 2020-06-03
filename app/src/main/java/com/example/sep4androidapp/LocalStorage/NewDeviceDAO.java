package com.example.sep4androidapp.LocalStorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;

import java.util.List;

@Dao
public interface NewDeviceDAO {


    @Insert
    void insertNewDevice(NewDeviceModel deviceModel);

    @Query("SELECT*FROM newDevice_table")
    LiveData<List<NewDeviceModel>> getAllDevices();

    @Delete
    void deleteDevice(NewDeviceModel model);

//    @Query("SELECT*FROM newDevice_table WHERE deviceId=:deviceId")
//    void getDevicesById(String deviceId);

    @Query("DELETE FROM newDevice_table")
    void deleteAllDevices();
}
