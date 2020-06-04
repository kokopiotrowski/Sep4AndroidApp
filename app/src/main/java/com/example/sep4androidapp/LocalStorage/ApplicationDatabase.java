package com.example.sep4androidapp.LocalStorage;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Entities.Preferences;

@Database(entities = {Preferences.class, NewDeviceModel.class}, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {
    private static ApplicationDatabase instance;
    public abstract PrefDAO prefDAO();
    public abstract NewDeviceDAO newDeviceDAO();

    public static synchronized ApplicationDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ApplicationDatabase.class, "applicationDatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
