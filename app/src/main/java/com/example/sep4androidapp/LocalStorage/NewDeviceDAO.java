package com.example.sep4androidapp.LocalStorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.sep4androidapp.Entities.NewDeviceModel;

import java.util.List;

@Dao
public interface NewDeviceDAO {
    @Insert
    void insertNewDevice(NewDeviceModel deviceModel);

    @Delete
    void deleteDevice(NewDeviceModel device);

    @Query("SELECT*FROM newDevice_table")
    LiveData<List<NewDeviceModel>> getAllDevices();

}
