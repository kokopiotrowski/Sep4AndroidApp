package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.connection.responses.FactResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FactApi {
    @GET("facts/random")
    Call<FactResponse> getRandomFact();

    @GET("facts")
    Call<List<Fact>> getFact();
}
