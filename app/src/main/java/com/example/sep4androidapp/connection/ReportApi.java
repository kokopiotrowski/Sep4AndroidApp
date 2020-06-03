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
import retrofit2.http.Query;

public interface ReportApi {

    @GET("reports/{deviceId}")

    Call<ReportResponse> getReport(@Path("deviceId") String deviceId);

    @GET("reports/{deviceId}")
    Call<ReportResponse> getReport(@Path("deviceId") String deviceId, @Query("dateStart") String dateStart,
                                   @Query("dateFinish") String dateFinish);
//@Path("deviceId") int deviceId
    @GET("reports/sleeps/{sleepId}")
    Call<SleepDataResponse> getSleepData(@Path("sleepId") int sleepId);

}
