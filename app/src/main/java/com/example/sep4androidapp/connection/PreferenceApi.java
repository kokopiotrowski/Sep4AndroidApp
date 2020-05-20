package com.example.sep4androidapp.connection;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PreferenceApi {


    @GET("preferences?devId={deviceId}")
    Call<PreferencesResponse> getPreferences(@Path("deviceId") int deviceId);


    @Headers("Content-Type: application/json")
    @PUT("preferences")
    Call updatePreferences(@Body Preferences preferences);


}
