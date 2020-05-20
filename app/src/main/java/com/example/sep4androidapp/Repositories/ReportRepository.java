package com.example.sep4androidapp.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.connection.RoomConditionApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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


    public void updateRoomCondition(int deviceId){

        RoomConditionApi roomConditionApi = ServiceGenerator.getRoomConditionApi();
        Call<RoomConditionResponse> call = roomConditionApi.getRoomCondition(deviceId);
        call.enqueue(new Callback<RoomConditionResponse>() {
            @Override
            public void onResponse(Call<RoomConditionResponse> call, Response<RoomConditionResponse> response) {
                if (response.code() == 200){

                    roomCondition.setValue(response.body().getRoomCondition());
                }

            }

            @Override
            public void onFailure(Call<RoomConditionResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });


    }

    public LiveData<RoomCondition> getRoomCondition(){

        return roomCondition;
    }
}
