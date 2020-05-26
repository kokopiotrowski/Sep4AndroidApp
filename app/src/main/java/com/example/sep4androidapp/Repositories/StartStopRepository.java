package com.example.sep4androidapp.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.StartStop;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.SleepTrackingApi;
import com.example.sep4androidapp.connection.responses.StartStopResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class StartStopRepository {
    private static StartStopRepository instance;
    private MutableLiveData<StartStop> startStop;
    private SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
    private MutableLiveData<Boolean> status;


    private StartStopRepository() {
        status = new MutableLiveData<>();
        startStop = new MutableLiveData<>();
    }

    public static synchronized StartStopRepository getInstance() {
        if (instance == null) {
            instance = new StartStopRepository();
        }
        return instance;
    }

    //https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845

    public void start(String deviceId) {
        SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
        Call<StartStopResponse> call = sleepTrackingApi.startDevice(deviceId);
        call.enqueue(new Callback<StartStopResponse>() {
            @Override
            public void onResponse(Call<StartStopResponse> call, Response<StartStopResponse> response) {
                if (response.code() == 200) {
                    Log.i(TAG, "Zolly1 " + response.code());

                } else {
                    Log.i(TAG, "Zolly2 " + response.code());
                }
            }

            @Override
            public void onFailure(Call<StartStopResponse> call, Throwable t) {
                Log.e(TAG, "Zolly3 ");
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
                    Log.i(TAG, "Zolly1 " + response.code());

                } else {
                    Log.i(TAG, "Zolly2 " + response.code());
                }
            }

            @Override
            public void onFailure(Call<StartStopResponse> call, Throwable t) {
                Log.e(TAG, "Zolly3 ");
            }
        });
    }

    public void receiveStatus(String deviceId) {
        SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
        Call<Boolean> call = sleepTrackingApi.getStatus(deviceId);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.i("TAG", "zollyresponserepo: " + response.code() + " " +  response.body());
                if(response.code() == 200)
                {
                    Log.i("TAG", "kkStatus received: " + response.body());
                    status.setValue(response.body());
                    Log.i("TAG", "kkStatus placed: " + status);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("TAG", "response: " + t.getMessage());
            }
        });

    }

    public LiveData<StartStop> getStartStop() {
        return startStop;
    }

    public MutableLiveData<Boolean> getStatus()
    {
        Log.i("TAG", "kkStatus to return: " + status);
        return status;
    }

}
