package com.example.sep4androidapp.connection;


import com.example.sep4androidapp.Entities.RoomCondition;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface RoomConditionApi {

    @GET("roomConditions/get/deviceId={id}")
    @FormUrlEncoded
    public Call<RoomCondition> getRoomConditions(@Path("id") int id);
}
