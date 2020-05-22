package com.example.sep4androidapp.connection;


import com.example.sep4androidapp.connection.responses.SleepDataResponse;
import com.example.sep4androidapp.connection.responses.SleepSessionResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportApi {

    @GET("reports/{deviceId}")
    Call getReport(@Path("deviceId") int deviceId);

    @GET("reports/sleeps/1")
    Call<SleepDataResponse> getSleepData();

}
