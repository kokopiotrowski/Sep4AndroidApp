package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.connection.responses.FactResponse;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FactApi {

    @GET("facts/random")
    public Call < FactResponse > getRandomFact();


    @GET("facts")
    public Call< List< Fact > > getFact();

    //@FormUrlEncoded
    //public Call<Fact> getFact();
}
