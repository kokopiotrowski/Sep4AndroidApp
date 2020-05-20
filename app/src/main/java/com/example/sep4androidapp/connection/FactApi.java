package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FactApi {
    @GET("facts")
    @FormUrlEncoded
    public Call<Fact> getFact();
}
