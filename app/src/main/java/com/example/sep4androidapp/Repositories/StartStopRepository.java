package com.example.sep4androidapp.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.connection.ApiCallBack;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.SleepTrackingApi;
import com.example.sep4androidapp.connection.responses.StartStopResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartStopRepository {
    private static StartStopRepository instance;

    private StartStopRepository() {
    }

    public static synchronized StartStopRepository getInstance() {
        if (instance == null) {
            instance = new StartStopRepository();
        }
        return instance;
    }

    public void start(String deviceId) {
        SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
        Call<StartStopResponse> call = sleepTrackingApi.startDevice(deviceId);
        call.enqueue(new Callback<StartStopResponse>() {
            @Override
            public void onResponse(Call<StartStopResponse> call, Response<StartStopResponse> response) {
                if (response.code() == 200) {
                    Log.i("StartStopRepo", "Successfully started device");
                } else {
                    Log.i("StartStopRepo", "Response during starting: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<StartStopResponse> call, Throwable t) {
                Log.i("StartStopRepo", "Start failed: " + t.getMessage());
            }
        });
    }

    public void stop(String deviceId) {
        SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
        Call<StartStopResponse> call = sleepTrackingApi.stopDevice(deviceId);
        call.enqueue(new Callback<StartStopResponse>() {
            @Override
            public void onResponse(Call<StartStopResponse> call, Response<StartStopResponse> response) {
                if (response.code() == 200) {
                    Log.i("StartStopRepo", "Successfully stopped device");
                } else {
                    Log.i("StartStopRepo", "Response during stopping: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<StartStopResponse> call, Throwable t) {
                Log.i("StartStopRepo", "Stopping failed: " + t.getMessage());
            }
        });
    }

    public void receiveStatus(String deviceId, final ApiCallBack callBack) {
        SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
        Call<Boolean> call = sleepTrackingApi.getStatus(deviceId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        callBack.onResponse(response.body());
                    }
                } else {
                    Log.i("StartStopRepo", "Response during receiveStatus: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("StartStopRepo", "Receiving failed: " + t.getMessage());
            }
        });
    }
}
