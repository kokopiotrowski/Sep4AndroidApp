package com.example.sep4androidapp.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.LocalStorage.ApplicationDatabase;
import com.example.sep4androidapp.LocalStorage.NewDeviceDAO;
import com.example.sep4androidapp.LocalStorage.PrefDAO;


import com.example.sep4androidapp.connection.PreferenceApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PreferencesRepository {
    private PrefDAO prefDao;
    private NewDeviceDAO newDeviceDAO;
    private static PreferencesRepository instance;
    private LiveData<List<Preferences>> allPreferences;
    private MutableLiveData<Preferences> preferences;
    private static MutableLiveData<List<Device>> list;
    private LiveData<List<NewDeviceModel>> allDevices;

    private PreferencesRepository(Application application) {
        ApplicationDatabase appDatabase = ApplicationDatabase.getInstance(application);

        prefDao = appDatabase.prefDAO();
        newDeviceDAO = appDatabase.newDeviceDAO();

        //pre = new MutableLiveData<>();

        preferences = new MutableLiveData<>();

        list = new MutableLiveData<>();

        allPreferences = prefDao.getAllPreferences();

        allDevices = newDeviceDAO.getAllDevices();
    }

    public static synchronized PreferencesRepository getInstance(Application application) {
        if (instance == null)
            instance = new PreferencesRepository(application);
        return instance;
    }

    public void insertDevice(NewDeviceModel model){
        new InsertNewDeviceAsync(newDeviceDAO).execute(model);
    }

//    public void deleteDevice(Device device){
//        new DeleteDeviceAsync(appDao).execute(device);
//    }

    public LiveData<List<NewDeviceModel>> getAllDevices(){
        return allDevices;
    }

    public static LiveData<List<Device>> getList() {
        return list;
    }

    public LiveData<List<Preferences>> getAllPreferences() {
        return allPreferences;
    }


    public void insert(Preferences preferences) {
        new InsertPreferencesAsync(prefDao).execute(preferences);
    }

    public void update(Preferences preferences) {
        new UpdatePreferencesAsync(prefDao).execute(preferences);
    }

    private static class InsertPreferencesAsync extends AsyncTask<Preferences, Void, Void> {
        private PrefDAO prefDAO;

        private InsertPreferencesAsync(PrefDAO prefDAO) {
            this.prefDAO = prefDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            prefDAO.insertPreference(preferences[0]);
            return null;
        }
    }

    private static class UpdatePreferencesAsync extends AsyncTask<Preferences, Void, Void> {
        private PrefDAO prefDAO;

        private UpdatePreferencesAsync(PrefDAO prefDAO) {
            this.prefDAO = prefDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            prefDAO.updatePreference(preferences[0]);
            return null;
        }
    }

    private static class InsertNewDeviceAsync extends AsyncTask<NewDeviceModel, Void, Void> {
        private NewDeviceDAO newDeviceDAO;

        private InsertNewDeviceAsync(NewDeviceDAO newDeviceDAO) {
            this.newDeviceDAO = newDeviceDAO;
        }

        @Override
        protected Void doInBackground(NewDeviceModel... newDeviceModels) {
            newDeviceDAO.insertNewDevice(newDeviceModels[0]);
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




}
