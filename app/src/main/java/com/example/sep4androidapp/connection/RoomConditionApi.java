package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.connection.responses.RoomConditionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RoomConditionApi {
    @GET("room-conditions/{deviceId}")
    Call<RoomConditionResponse> getRoomCondition(@Path("deviceId") String deviceId);

    @GET("room-conditions/{deviceId}/latest")
    Call<RoomConditionResponse> getLastRoomCondition(@Path("deviceId") String deviceId);
}
