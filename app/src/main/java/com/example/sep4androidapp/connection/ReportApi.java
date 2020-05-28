package com.example.sep4androidapp.connection;


import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.connection.responses.ReportResponse;
import com.example.sep4androidapp.connection.responses.SleepDataResponse;
import com.example.sep4androidapp.connection.responses.SleepSessionResponse;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportApi {

    @GET("reports/device1?dateStart=2010-10-10&dateFinish=2020-10-10")
    Call<ReportResponse> getReport();
//@Path("deviceId") int deviceId
    @GET("reports/sleeps/1")
    Call<SleepDataResponse> getSleepData();

}
