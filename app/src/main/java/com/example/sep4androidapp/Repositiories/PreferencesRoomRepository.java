package com.example.sep4androidapp.Repositiories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.LocalStorage.PreferencesDAO;
import com.example.sep4androidapp.LocalStorage.PreferencesDatabase;

import java.util.List;

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

}
