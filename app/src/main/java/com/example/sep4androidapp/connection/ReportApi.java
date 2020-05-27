package com.example.sep4androidapp.connection;


import com.example.sep4androidapp.Entities.SleepSession;
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

    @GET("reports/{deviceId}?dateStart={dStart}&dateFinish={dFinish}")
    Call<List<SleepSession>> getReport(@Path("deviceId") int deviceId, @Path("dStart") String dateStart, @Path("dFinish") String dateFinish);

    @GET("reports/sleeps/1")
    Call<SleepDataResponse> getSleepData();

}
