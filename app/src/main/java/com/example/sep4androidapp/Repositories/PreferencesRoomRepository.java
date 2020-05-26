package com.example.sep4androidapp.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.LocalStorage.PreferencesDAO;
import com.example.sep4androidapp.LocalStorage.PreferencesDatabase;
import com.example.sep4androidapp.connection.PreferenceApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.PreferencesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PreferencesRoomRepository {
    private PreferencesDAO preferencesDao;
    private static PreferencesRoomRepository instance;
    private LiveData< List< Preferences > > allPreferences;
    private MutableLiveData< Preferences > pre;

    private PreferencesRoomRepository(Application application) {
        PreferencesDatabase preferencesDatabase = PreferencesDatabase.getInstance(application);
        preferencesDao = preferencesDatabase.preferencesDAO();
        pre = new MutableLiveData<>();

        allPreferences = preferencesDao.getAllPreferences();

        //preferences = preferencesDao.getAllPreferences().getValue().get(0);
    }

    public static synchronized PreferencesRoomRepository getInstance(Application application) {
        if (instance == null)
            instance = new PreferencesRoomRepository(application);
        return instance;
    }

    public LiveData< List< Preferences > > getAllPreferences() {
        return allPreferences;
    }

    public void insert(Preferences preferences) {
        new InsertPreferencesAsync(preferencesDao).execute(preferences);
    }

    public void update(Preferences preferences) {
        new UpdatePreferencesAsync(preferencesDao).execute(preferences);
    }

    public void deletePreference(Preferences preferences) {
        new DeletePreferenceAsync(preferencesDao).execute();
    }

    public void deleteAllPreferences() {
        new DeleteAllPreferencesAsync(preferencesDao).execute();
    }

    private static class InsertPreferencesAsync extends AsyncTask< Preferences, Void, Void > {
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

    private static class UpdatePreferencesAsync extends AsyncTask< Preferences, Void, Void > {
        private PreferencesDAO preferencesDAO;

        private UpdatePreferencesAsync(PreferencesDAO preferencesDAO) {
            this.preferencesDAO = preferencesDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            preferencesDAO.updatePreference(preferences[0]);
            return null;
        }
    }

    private static class DeletePreferenceAsync extends AsyncTask< Preferences, Void, Void > {
        private PreferencesDAO preferencesDAO;

        private DeletePreferenceAsync(PreferencesDAO preferencesDAO) {
            this.preferencesDAO = preferencesDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            preferencesDAO.deletePreference(preferences[0]);
            return null;
        }
    }

    private static class DeleteAllPreferencesAsync extends AsyncTask< Preferences, Void, Void > {
        private PreferencesDAO preferencesDAO;

        private DeleteAllPreferencesAsync(PreferencesDAO preferencesDAO) {
            this.preferencesDAO = preferencesDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            preferencesDAO.deleteAllPreferences();
            return null;
        }
    }

    //_________________________________________________________________________


    // GET API
    public void showPreferences() {
        PreferenceApi preferenceApi = ServiceGenerator.getPreferenceApi();
        Call< PreferencesResponse > call = preferenceApi.getPreferences();
        call.enqueue(new Callback< PreferencesResponse >() {
            @Override
            public void onResponse(Call< PreferencesResponse > call, Response< PreferencesResponse > response) {

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
                    //preferencesDao.insertPreference(P1);
                    //pre.setValue(P1);
                    //pre.postValue(P1);
                    pre.setValue(response.body().getPre());

                    Log.i(TAG, "Pouneh0" + response.code());

                } else {
                    Log.i(TAG, "Pouneh2 " + response.code());
                }
            }
            @Override
            public void onFailure(Call< PreferencesResponse > call, Throwable t) {
                Log.e(TAG, "Pouneh3 ");
            }
        });
    }

    public LiveData< Preferences > getPre() { return pre; }


    // PUT API
    public void updatePrefrences(Preferences preference) {

        PreferenceApi preferenceApi = ServiceGenerator.getPreferenceApi();
        Call< PreferencesResponse > call = preferenceApi.updatePreferences(preference);
        call.enqueue(new Callback< PreferencesResponse >() {
            @Override
            public void onResponse(Call< PreferencesResponse > call, Response< PreferencesResponse > response) {
                Log.i(TAG, "Pouneh1 " + response.code());
            }

            @Override
            public void onFailure(Call< PreferencesResponse > call, Throwable t) {
                Log.i(TAG, "Pouneh2");
            }
        });
    }

    public LiveData< Preferences > getPreFrence() {
        return pre;
    }


}
