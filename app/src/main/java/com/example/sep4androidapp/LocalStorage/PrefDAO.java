package com.example.sep4androidapp.LocalStorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sep4androidapp.Entities.Preferences;

import java.util.List;

@Dao
public interface PrefDAO {
    @Insert
    void insertPreference(Preferences preferences);

    @Update
    void updatePreference(Preferences preferences);

    @Delete
    void deletePreference(Preferences preferences);

    @Query("SELECT * FROM preferences_table WHERE deviceId = :deviceId ")
    Preferences getPreferencesById(String deviceId);
}
