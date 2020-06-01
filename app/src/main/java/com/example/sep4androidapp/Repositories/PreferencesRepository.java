package com.example.sep4androidapp.Repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.connection.PreferenceApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PreferencesRepository {
    private static PreferencesRepository instance;
    private MutableLiveData<Preferences> preferences;

    private PreferencesRepository()
    {
        preferences = new MutableLiveData<>();
    }

    public static synchronized PreferencesRepository getInstance() {
        if (instance == null)
            instance = new PreferencesRepository();
        return instance;
    }

    // GET API
    public void showPreferences(String deviceId) {
        PreferenceApi preferenceApi = ServiceGenerator.getPreferenceApi();
        Call<PreferencesResponse> call = preferenceApi.getPreferences(deviceId);
        call.enqueue(new Callback<PreferencesResponse>() {
            @Override
            public void onResponse(Call<PreferencesResponse> call, Response<PreferencesResponse> response) {
                if (response.code() == 200) {
                    Preferences P1 = new Preferences(
                            response.body().getDeviceId()
                            , response.body().isRegulationEnabled()
                            , response.body().getCo2Max()
                            , response.body().getCo2Min()
                            , response.body().getHumidityMax()
                            , response.body().getHumidityMin()
                            , response.body().getTemperatureMin()
                            , response.body().getTemperatureMax());
                    preferences.setValue(P1);

                    Log.i(TAG, "Pouneh0" + response.code());

                } else {
                    Log.i(TAG, "Pouneh2 " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PreferencesResponse> call, Throwable t) {
                Log.e(TAG, "Pouneh3 ");
            }
        });
    }

    // PUT API
    public void updatePrefrences(Preferences preference) {

        PreferenceApi preferenceApi = ServiceGenerator.getPreferenceApi();
        Call<PreferencesResponse> call = preferenceApi.updatePreferences(preference);
        call.enqueue(new Callback<PreferencesResponse>() {
            @Override
            public void onResponse(Call<PreferencesResponse> call, Response<PreferencesResponse> response) {
                Log.i(TAG, "Pouneh1 " + response.code());
            }

            @Override
            public void onFailure(Call<PreferencesResponse> call, Throwable t) {
                Log.i(TAG, "Pouneh2");
            }
        });
    }

    public LiveData<Preferences> getPreferences() {
        return preferences;
    }
}
