package com.example.sep4androidapp.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.StartStop;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.SleepTrackingApi;
import com.example.sep4androidapp.connection.responses.StartStopResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartStopRepository {
    private static StartStopRepository instance;
    private MutableLiveData< StartStop > startStop;

    private StartStopRepository(){
        startStop = new MutableLiveData<>();

    }

    public static synchronized StartStopRepository getInstance(){
        if (instance == null){
            instance = new StartStopRepository();
        }
        return instance;
    }


    public MutableLiveData< StartStop > startStopTracking(){



        return null;
    }





}
