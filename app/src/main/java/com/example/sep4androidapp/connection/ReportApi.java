package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.connection.responses.ReportResponse;
import com.example.sep4androidapp.connection.responses.SleepDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReportApi {
    @GET("reports/{deviceId}")
    Call<ReportResponse> getReport(@Path("deviceId") String deviceId);

    @GET("reports/{deviceId}")
    Call<ReportResponse> getReport(@Path("deviceId") String deviceId, @Query("dateStart") String dateStart,
                                   @Query("dateFinish") String dateFinish);

    @GET("reports/sleeps/{sleepId}")
    Call<SleepDataResponse> getSleepData(@Path("sleepId") int sleepId);
}
