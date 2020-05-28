package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FactApi {

    @GET("fact/random")
    public Call <Fact> getRandomFact();


    @GET("facts")
    //@FormUrlEncoded
    public Call< List< Fact > > getFact();

    //public Call<Fact> getFact();
}
