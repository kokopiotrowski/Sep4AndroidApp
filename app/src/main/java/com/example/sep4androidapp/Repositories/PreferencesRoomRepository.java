package com.example.sep4androidapp.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.LocalStorage.PreferencesDAO;
import com.example.sep4androidapp.LocalStorage.PreferencesDatabase;
import com.example.sep4androidapp.connection.PreferenceApi;
import com.example.sep4androidapp.connection.RoomConditionApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.SleepTrackingApi;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;
import com.example.sep4androidapp.connection.responses.StartStopResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PreferencesRoomRepository {

    private PreferencesDAO preferencesDao;
    private static PreferencesRoomRepository instance;
    private LiveData<List<Preferences>> allPreferences;

    private PreferencesRoomRepository(Application application){
        PreferencesDatabase preferencesDatabase = PreferencesDatabase.getInstance(application);
        preferencesDao = preferencesDatabase.preferencesDAO();
        allPreferences = preferencesDao.getAllPreferences();
    }

    public static synchronized PreferencesRoomRepository getInstance(Application application){
        if(instance == null)
            instance = new PreferencesRoomRepository(application);
        return instance;
    }

    public LiveData<List<Preferences>> getAllPreferences(){
        return allPreferences;
    }

    public void insert(Preferences preferences){
        new InsertPreferencesAsync(preferencesDao).execute(preferences);
    }

    public void update(Preferences preferences){
        new UpdatePreferencesAsync(preferencesDao).execute(preferences);
    }

    private static class InsertPreferencesAsync extends AsyncTask<Preferences,Void,Void> {

        private PreferencesDAO preferencesDAO;

        private InsertPreferencesAsync(PreferencesDAO preferencesDAO) {
            this.preferencesDAO = preferencesDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            preferencesDAO.insertPreference(preferences[0]);
            return null;
        }
    }

    private static class UpdatePreferencesAsync extends AsyncTask<Preferences,Void,Void>{

        private PreferencesDAO preferencesDAO;

        private UpdatePreferencesAsync(PreferencesDAO preferencesDAO){
            this.preferencesDAO = preferencesDAO;
        }
        @Override
        protected Void doInBackground(Preferences... preferences) {
            preferencesDAO.updatePreference(preferences[0]);
            return null;
        }
    }

    private static class DeletePreferenceAsync extends AsyncTask<Preferences,Void,Void>{

        private PreferencesDAO preferencesDAO;

        private DeletePreferenceAsync(PreferencesDAO preferencesDAO){
            this.preferencesDAO = preferencesDAO;
        }
        @Override
        protected Void doInBackground(Preferences... preferences) {
            preferencesDAO.deletePreference(preferences[0]);
            return null;
        }


    }

    //_________________________________________________________________________


    public void showPrefrences() {
        PreferenceApi preferenceApi = ServiceGenerator.getPreferenceApi();
        Call< PreferencesResponse > call = preferenceApi.getPreferences(1);
        call.enqueue(new Callback<PreferencesResponse>() {
            @Override
            public void onResponse(Call<PreferencesResponse> call, Response<PreferencesResponse> response) {
                Log.i(TAG, "Pouneh0");
                if (response.code() == 200) {
                    Log.i(TAG, "Pouneh1 " + response.code());

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



   /*


    public void updatePrefrences(){

        PreferenceApi preferenceApi = ServiceGenerator.getPreferenceApi();
        Call< PreferencesResponse > call = preferenceApi.updatePreferences();
        call.enqueue(new Callback<PreferencesResponse>() {
            @Override
            public void onResponse(Call<PreferencesResponse> call, Response<PreferencesResponse> response) {
                if (response.code() == 200){

                    allPreferences.setValue(response.body().getRoomCondition());
                }
            }

            @Override
            public void onFailure(Call<PreferencesResponse> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());

            }
        });


    }

    */


}
