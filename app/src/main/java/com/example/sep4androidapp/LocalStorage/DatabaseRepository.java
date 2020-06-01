package com.example.sep4androidapp.LocalStorage;

import android.app.Application;
import android.os.AsyncTask;
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

public class DatabaseRepository {
    private AppDAO appDao;
    private static DatabaseRepository instance;
    private LiveData<List<Preferences>> allPreferences;

    private static MutableLiveData<List<Device>> list;

    private DatabaseRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        appDao = appDatabase.preferencesDAO();
        list = new MutableLiveData<>();
        allPreferences = appDao.getAllPreferences();
    }

    public static synchronized DatabaseRepository getInstance(Application application) {
        if (instance == null)
            instance = new DatabaseRepository(application);
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
}
