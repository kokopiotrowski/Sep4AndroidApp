package com.example.sep4androidapp.connection;


import android.telecom.CallScreeningService;

import com.example.sep4androidapp.Entities.User;
import com.example.sep4androidapp.connection.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserApi {

    @POST("user/create")
    @FormUrlEncoded
    Call createUser();

    @GET("user/get/{id}")
    Call getUser(@Path("id") int id);



}
