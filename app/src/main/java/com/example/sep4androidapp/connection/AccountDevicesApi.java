package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AccountDevicesApi {

    @GET("devices")
    public Call<List<Device>> getDevices();

    @GET("devices/available")
    Call<List<String>> getAvailableDevices();

    @Headers("Content-Type: application/json")
    @POST("devices")
    Call<Device> addDevice(@Body NewDeviceModel device);

//    @GET("devices?userId={userId}")
//    @FormUrlEncoded
//    Call<DeviceResponse> getDeviceByUserId(@Path("userId") int userId);
//

//
//    @Headers("Content-Type: application/json")
//    @PUT("devices")
//    @FormUrlEncoded
//    Call updateDevice(@Body Device device);

}
