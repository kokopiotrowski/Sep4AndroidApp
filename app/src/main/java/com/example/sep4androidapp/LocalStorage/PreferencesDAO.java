//package com.example.sep4androidapp.LocalStorage;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.example.sep4androidapp.Entities.NewDeviceModel;
//import com.example.sep4androidapp.Entities.Preferences;
//
//import java.util.List;
//
//@Dao
//public interface PreferencesDAO {
//
//    //--------preferences
//
//    @Insert
//    void insertPreference(Preferences preferences);
////
//    @Update
//    void updatePreference(Preferences preferences);
//
////    @Delete
////    void deletePreference(Preferences preferences);
//
////    @Query("DELETE FROM preferences_table where deviceId= :deviceId")
////    void deletePreference(int deviceId);
//
////    @Query("DELETE FROM preferences_table")
////    void deleteAllPreferences();
//
//    @Query("SELECT*FROM preferences_table")
//    LiveData<List<Preferences>> getAllPreferences();
//
//
//}
