package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.connection.responses.RatingResponse;
import com.example.sep4androidapp.connection.responses.StartStopResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SleepTrackingApi {
    @POST("tracking/start/{deviceId}")
    Call<StartStopResponse> startDevice(@Path("deviceId") String deviceId);

    @PUT("tracking/stop/{deviceId}")
    Call<StartStopResponse> stopDevice(@Path("deviceId") String deviceId);

    @GET("tracking/{deviceId}")
    Call<Boolean> getStatus(@Path("deviceId") String deviceId);

    @PUT("tracking/{sleepId}/{rating}")
    Call<RatingResponse> rateSleep(@Path("sleepId") int sleepId, @Path("rating") int rating);


}
