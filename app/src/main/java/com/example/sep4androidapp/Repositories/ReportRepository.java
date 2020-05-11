package com.example.sep4androidapp.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.RoomCondition;

import java.util.Date;


public class ReportRepository {

    private static ReportRepository instance;
    private MutableLiveData<RoomCondition> roomCondition;

    private ReportRepository (){
        roomCondition = new MutableLiveData<>();

    }

    public static synchronized ReportRepository getInstance(){
        if (instance == null){
            instance = new ReportRepository();
        }
        return instance;
    }


    public void updateRoomCondition(){


    }

    public LiveData<RoomCondition> getRoomCondition(){

        return roomCondition;
    }
}
