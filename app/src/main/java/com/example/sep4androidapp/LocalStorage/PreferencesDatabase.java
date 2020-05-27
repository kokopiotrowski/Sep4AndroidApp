package com.example.sep4androidapp.LocalStorage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sep4androidapp.Entities.Preferences;

@Database(entities ={Preferences.class}, version = 2)
public abstract class PreferencesDatabase extends RoomDatabase {

    private static PreferencesDatabase instance;

    public abstract PreferencesDAO preferencesDAO();

    public static synchronized PreferencesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PreferencesDatabase.class, "preferences_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
