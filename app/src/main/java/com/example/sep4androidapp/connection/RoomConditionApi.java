package com.example.sep4androidapp.connection;


import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface RoomConditionApi {

    @GET("room-conditions/0004A30B002181EC")
    public Call<RoomConditionResponse> getRoomCondition();
}
