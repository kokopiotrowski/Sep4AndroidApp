package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.connection.responses.DeviceResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountDevicesApi {

    @GET("devices?userId={userId}")
    @FormUrlEncoded
    Call<DeviceResponse> getDeviceByUserId(@Path("userId") int userId);

    @Headers("Content-Type: application/json")
    @POST("devices")
    @FormUrlEncoded
    Call addDevice(@Body Device device);

    @Headers("Content-Type: application/json")
    @PUT("devices")
    @FormUrlEncoded
    Call updateDevice(@Body Device device);

}