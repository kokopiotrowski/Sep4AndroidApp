package com.example.sep4androidapp.connection.apis;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PreferenceApi {
    @GET("preferences/{deviceId}")
    Call<PreferencesResponse> getPreferences(@Path("deviceId") String deviceId);

    @PUT("preferences")
    Call <PreferencesResponse>updatePreferences(@Body Preferences preferences);




}
