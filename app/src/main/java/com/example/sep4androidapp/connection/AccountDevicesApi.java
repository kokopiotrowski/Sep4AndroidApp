package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountDevicesApi {

    @GET("devices")
    Call<List<Device>> getDevices();

    @GET("devices/available")
    Call<List<String>> getAvailableDevices();

    @GET("devices/{deviceId}")
    Call<Device> getDevice(@Path("deviceId") String deviceId);

//    @Headers("Content-Type: application/json")
    @POST("devices")
    Call<Device> addDevice(@Body NewDeviceModel device);

    @DELETE("devices/{deviceId}")
    Call<Void> deleteDevice(@Path("deviceId") String deviceId);

}
