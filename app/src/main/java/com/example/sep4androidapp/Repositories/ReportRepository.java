package com.example.sep4androidapp.Repositories;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.SleepData;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.connection.ReportApi;
import com.example.sep4androidapp.connection.RoomConditionApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.ReportResponse;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;
import com.example.sep4androidapp.connection.responses.SleepDataResponse;
import com.example.sep4androidapp.connection.responses.SleepSessionResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReportRepository {

    private static ReportRepository instance;
    private MutableLiveData<RoomCondition> roomCondition;
    private MutableLiveData<SleepData> sleepData;
    private MutableLiveData<List<SleepSession>> sleepSessions;

    private ReportRepository (){
        roomCondition = new MutableLiveData<>();
        sleepData = new MutableLiveData<>();
        sleepSessions = new MutableLiveData<>();

    }

    public static synchronized ReportRepository getInstance(){
        if (instance == null){
            instance = new ReportRepository();
        }
        return instance;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateRoomCondition(String deviceId){

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
                Log.i("Retrofit", t.getMessage());

            }
        });
    }

    public LiveData<RoomCondition> getRoomCondition(){

        return roomCondition;
    }



    public void updateSleepData() {
        ReportApi reportApi = ServiceGenerator.getReportApi();
        Call<SleepDataResponse> call = reportApi.getSleepData();
        call.enqueue(new Callback<SleepDataResponse>() {

            @Override
            public void onResponse(Call<SleepDataResponse> call, Response<SleepDataResponse> response) {

                if (response.code() == 200){
                    sleepData.setValue(response.body().getSleepData());

                }
            }

            @Override
            public void onFailure(Call<SleepDataResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong in update sleep data :(");
                Log.i("Why", "" + t.getCause());
            }
        });
    }

    public LiveData<SleepData> getSleepData() {
        return sleepData;
    }

    public void updateSleepSessions(String deviceId){

        LocalDate today = LocalDate.now();
        LocalDate monthAgo = today.minusMonths(1);
        today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        monthAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ReportApi reportApi = ServiceGenerator.getReportApi();
        Call<ReportResponse> call = reportApi.getReport(deviceId);
        call.enqueue(new Callback<ReportResponse>() {

            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                if (response.code() == 200){
                    sleepSessions.setValue(response.body().getSleepSessions());
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong in update sleep data :(");
                Log.i("Why", "" + t.getCause());
            }

        });
    }

    public LiveData<List<SleepSession>> getSleepSessions()
    {
        return sleepSessions;
    }

}
