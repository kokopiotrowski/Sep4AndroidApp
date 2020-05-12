package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.connection.responses.RoomConditionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RoomConditionApi {

    @GET("condition/get/{id}")
    Call<RoomConditionResponse> getRoomCondition(@Path("id") int deviceId);
}
