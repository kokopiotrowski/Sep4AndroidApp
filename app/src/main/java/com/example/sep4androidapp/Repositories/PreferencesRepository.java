package com.example.sep4androidapp.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.LocalStorage.AppDAO;
import com.example.sep4androidapp.LocalStorage.AppDatabase;
import com.example.sep4androidapp.connection.PreferenceApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PreferencesRepository {
    private AppDAO appDao;
    private static PreferencesRepository instance;
    private LiveData<List<Preferences>> allPreferences;
    private MutableLiveData<Preferences> pre;
    private static MutableLiveData<List<Device>> list;

    private PreferencesRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        appDao = appDatabase.appDAO();
        pre = new MutableLiveData<>();
        list = new MutableLiveData<>();

        allPreferences = appDao.getAllPreferences();
    }

    public static synchronized PreferencesRepository getInstance(Application application) {
        if (instance == null)
            instance = new PreferencesRepository(application);
        return instance;
    }

    public static LiveData<List<Device>> getList() {
        return list;
    }

    public LiveData<List<Preferences>> getAllPreferences() {
        return allPreferences;
    }

    public void insert(Preferences preferences) {
        new InsertPreferencesAsync(appDao).execute(preferences);
    }

    public void update(Preferences preferences) {
        new UpdatePreferencesAsync(appDao).execute(preferences);
    }

    private static class InsertPreferencesAsync extends AsyncTask<Preferences, Void, Void> {
        private AppDAO appDAO;

        private InsertPreferencesAsync(AppDAO appDAO) {
            this.appDAO = appDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            appDAO.insertPreference(preferences[0]);
            return null;
        }
    }

    private static class UpdatePreferencesAsync extends AsyncTask<Preferences, Void, Void> {
        private AppDAO appDAO;

        private UpdatePreferencesAsync(AppDAO appDAO) {
            this.appDAO = appDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            appDAO.updatePreference(preferences[0]);
            return null;
        }
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
                    pre.setValue(P1);

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

    public LiveData<Preferences> getPre() {
        return pre;
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

    public LiveData<Preferences> getPreFrence() {
        return pre;
    }


}
