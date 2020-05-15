package com.example.sep4androidapp.connection;

import android.telecom.Call;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportApi {

    @GET("report/get/{id}")
    @FormUrlEncoded
    Call getReport(@Path("id") int id);
}
