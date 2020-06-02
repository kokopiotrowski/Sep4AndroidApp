//package com.example.sep4androidapp.LocalStorage;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import com.example.sep4androidapp.Entities.NewDeviceModel;
//
////@Database(entities ={Preferences.class}, version = 1)
//@Database(entities = {NewDeviceModel.class, NewDeviceModel.class}, version = 1)
//public abstract class AppDatabase extends RoomDatabase {
//
//    private static AppDatabase instance;
//
//    public abstract PreferencesDAO preferencesDAO();
//    public abstract NewDeviceDAO newDeviceDAO();
//
//    public static synchronized AppDatabase getInstance(Context context){
//        if(instance == null){
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    AppDatabase.class, "db")
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return instance;
//    }
//}
