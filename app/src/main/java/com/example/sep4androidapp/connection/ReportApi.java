package com.example.sep4androidapp.connection;


import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportApi {

    @GET("report/{deviceId}")
    Call getReport(@Path("deviceId") int deviceId);

}
