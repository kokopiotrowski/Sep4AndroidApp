package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.connection.responses.DeviceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountDevicesApi {

    @GET("devices")
    public Call<List<DeviceResponse>> getDevices();

//    @GET("devices?userId={userId}")
//    @FormUrlEncoded
//    Call<DeviceResponse> getDeviceByUserId(@Path("userId") int userId);
//
//    @Headers("Content-Type: application/json")
//    @POST("devices")
//    @FormUrlEncoded
//    Call addDevice(@Body Device device);
//
//    @Headers("Content-Type: application/json")
//    @PUT("devices")
//    @FormUrlEncoded
//    Call updateDevice(@Body Device device);

}
