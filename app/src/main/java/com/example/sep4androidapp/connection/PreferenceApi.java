package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PreferenceApi {

    @GET("preferences/fake_device1")
    Call<PreferencesResponse> getPreferences();

//    @GET("preferences/{deviceId}")
//  Call<PreferencesResponse> getPreferences(@Path("deviceId") String deviceId);

    @Headers("Content-Type: application/json")
    @PUT("preferences")
    Call <PreferencesResponse>updatePreferences(@Body Preferences preferences);




}
