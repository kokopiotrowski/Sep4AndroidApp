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

    @GET("preferences/0004A30B002181EC")
    public Call<PreferencesResponse> getPreferences();

//    @GET("preferences?devId={deviceId}") "room-conditions/0004A30B002181EC"
  //  Call<PreferencesResponse> getPreferences(@Path("deviceId") int deviceId);


    @Headers("Content-Type: application/json")
    @PUT("preferences")
    Call updatePreferences(@Body Preferences preferences);




}
