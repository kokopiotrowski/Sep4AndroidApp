package com.example.sep4androidapp.Repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Preferences;


import com.example.sep4androidapp.connection.PreferenceApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PreferencesRepository {

    private static PreferencesRepository instance;
    private MutableLiveData<Preferences> preferences;
    private static MutableLiveData<List<Device>> list;
    private DatabaseRepository databaseRepository;

    private PreferencesRepository(Application application) {
        preferences = new MutableLiveData<>();
        list = new MutableLiveData<>();
        databaseRepository = DatabaseRepository.getInstance(application);
    }

    public static synchronized PreferencesRepository getInstance(Application application) {
        if (instance == null)
            instance = new PreferencesRepository(application);
        return instance;
    }

    public static LiveData<List<Device>> getList() {
        return list;
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

    public LiveData<Preferences> getPreferences() {
        return preferences;
    }


    // PUT API
    public void savePreferencesToNetwork(Preferences p) {

        PreferenceApi preferenceApi = ServiceGenerator.getPreferenceApi();
        Call<PreferencesResponse> call = preferenceApi.updatePreferences(p);
        call.enqueue(new Callback<PreferencesResponse>() {
            @Override
            public void onResponse(Call<PreferencesResponse> call, Response<PreferencesResponse> response) {
                if(response.code() == 200)
                {
                    Preferences pref = databaseRepository.getPreferencesById(p.getDeviceId());
                    if(pref == null)
                    {
                        databaseRepository.insert(p);
                    }else{
                        p.setId(pref.getId());
                        databaseRepository.updatePreferencesToDb(p);
                    }

                }else{
                    Log.i("PreferencesRepo", "basszus " + response.code());
                }

            }

            @Override
            public void onFailure(Call<PreferencesResponse> call, Throwable t) {
                Log.i(TAG, "Pouneh2");
            }
        });
    }



}
