package com.example.sep4androidapp.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.LocalStorage.ApplicationDatabase;
import com.example.sep4androidapp.LocalStorage.NewDeviceDAO;
import com.example.sep4androidapp.LocalStorage.PrefDAO;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {
    private static DatabaseRepository instance;
    private PrefDAO prefDao;
    private NewDeviceDAO newDeviceDAO;
    private LiveData<List<Preferences>> allPreferences;
    private LiveData<List<NewDeviceModel>> allDevices;

    public DatabaseRepository(Application application) {
        ApplicationDatabase appDatabase = ApplicationDatabase.getInstance(application);
        prefDao = appDatabase.prefDAO();
        newDeviceDAO = appDatabase.newDeviceDAO();
        allPreferences = prefDao.getAllPreferences();
        allDevices = newDeviceDAO.getAllDevices();
    }

    public static synchronized DatabaseRepository getInstance(Application application) {
        if (instance == null)
            instance = new DatabaseRepository(application);
        return instance;
    }

    public void insertDevice(NewDeviceModel model) {
        new DatabaseRepository.InsertNewDeviceAsync(newDeviceDAO).execute(model);
    }

    public LiveData<List<NewDeviceModel>> getAllDevices() {
        return allDevices;
    }

    public Preferences getPreferencesById(String deviceId) {
        return prefDao.getPreferencesById(deviceId);
    }

    public void insert(Preferences preferences) {
        new InsertPreferencesAsync(prefDao).execute(preferences);
    }

    public void updatePreferencesToDb(Preferences preferences) {
        new UpdatePreferencesAsync(prefDao).execute(preferences);
    }

    public void deletePreferences(Preferences preferences)
    {
        new DeletePreferencesAsync(prefDao).execute(preferences);
    }

    public void deleteDevice(NewDeviceModel device) {
        new DeleteDeviceAsyncTask(newDeviceDAO).execute(device);
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

    private static class DeletePreferencesAsync extends AsyncTask<Preferences, Void, Void> {
        private PrefDAO prefDAO;

        private DeletePreferencesAsync(PrefDAO prefDAO) {
            this.prefDAO = prefDAO;
        }

        @Override
        protected Void doInBackground(Preferences... preferences) {
            prefDAO.deletePreference(preferences[0]);
            return null;
        }
    }

    private static class DeleteDeviceAsyncTask extends AsyncTask<NewDeviceModel, Void, Void> {
        private NewDeviceDAO deviceDao;

        private DeleteDeviceAsyncTask(NewDeviceDAO dao) {
            this.deviceDao = dao;
        }

        @Override
        protected Void doInBackground(NewDeviceModel... newDeviceModels) {
            deviceDao.deleteDevice(newDeviceModels[0]);
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
}
