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
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;
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

    public void start() {

        SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
        Call< StartStopResponse > call = sleepTrackingApi.startDevice();
        call.enqueue(new Callback< StartStopResponse >() {
            @Override
            public void onResponse(Call< StartStopResponse > call, Response< StartStopResponse > response) {
                Log.i(TAG, "Zolly0.");
                if (response.code() == 200) {
                    Log.i(TAG, "Zolly1." + response.code());

                } else {
                    Log.i(TAG, "Zolly2" + response.code());
                }
            }

            @Override
            public void onFailure(Call< StartStopResponse > call, Throwable t) {
                Log.e(TAG, "Zolly3");
            }
        });

        /*
        sleepTrackingApi.startDevice(1).enqueue(new Callback<StartStopResponse>() {
            @Override
            public void onResponse(Call<StartStopResponse> call, Response<StartStopResponse> response) {
                Log.i(TAG, "Zolly0." );
                if(response.isSuccessful()) {

                    //startStop.setValue(response.body().getStartStop());

                    Log.i(TAG, "Zolly1." + response.code());
                }else
                {
                    Log.i(TAG,"Zolly2" +response.code());
                }
            }

            @Override
            public void onFailure(Call<StartStopResponse> call, Throwable t) {
                Log.e(TAG, "Zolly3");
            }
        });*/
    }

    public void stop() {
        SleepTrackingApi sleepTrackingApi = ServiceGenerator.getSleepTrackingApi();
        Call< StartStopResponse > call = sleepTrackingApi.stopDevice();
        call.enqueue(new Callback< StartStopResponse >() {
            @Override
            public void onResponse(Call< StartStopResponse > call, Response< StartStopResponse > response) {
                Log.i(TAG, "Zolly0.");
                if (response.code() == 200) {
                    Log.i(TAG, "Zolly1" + response.code());

                } else {
                  Log.i(TAG, "Zolly2" + response.code());
                }
            }

            @Override
            public void onFailure(Call< StartStopResponse > call, Throwable t) {
                Log.e(TAG, "Zolly3");
            }
        });



    }

    public LiveData< StartStop > getStartStop() {

        return startStop;
    }
}
