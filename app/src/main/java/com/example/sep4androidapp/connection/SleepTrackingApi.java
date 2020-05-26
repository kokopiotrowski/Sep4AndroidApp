package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.connection.responses.StartStopResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SleepTrackingApi {
    @POST("tracking/start/0004A30B002181EC")
    Call<StartStopResponse> startDevice();

    @PUT("tracking/stop/0004A30B002181EC")
    Call<StartStopResponse> stopDevice();

    @GET("tracking/0004A30B002181EC")
    Call<Boolean> getStatus();


    Call< StartStopResponse> getStartStopTrackingApi();



}
