package com.example.sep4androidapp.Repositories;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.StartStop;
import com.example.sep4androidapp.connection.RoomConditionApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.SleepTrackingApi;
import com.example.sep4androidapp.connection.responses.StartStopResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class StartStopRepository {
    private static StartStopRepository instance;
    private MutableLiveData< StartStop > startStop;
    private SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();

    private StartStopRepository() {
        startStop = new MutableLiveData<>();

    }

    public static synchronized StartStopRepository getInstance() {
        if (instance == null) {
            instance = new StartStopRepository();
        }
        return instance;
    }

    //https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845

    public void startDevice() {

        sleepTrackingApi.startDevice(1).enqueue(new Callback<StartStopResponse>() {
            @Override
            public void onResponse(Call<StartStopResponse> call, Response<StartStopResponse> response) {

                if(response.isSuccessful()) {
                    startStop.setValue(response.body().getStartStop());
                    Log.i(TAG, "Device is started." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<StartStopResponse> call, Throwable t) {
                Log.e(TAG, "Unable to start the device.");
            }
        });
    }

    public LiveData< StartStop > getStartStop(){

        return startStop;
    }
}
