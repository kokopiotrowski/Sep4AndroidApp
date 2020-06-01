package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.LocalStorage.DatabaseRepository;
import com.example.sep4androidapp.Repositories.PreferencesRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;

import java.util.List;

public class PreferencesViewModel extends AndroidViewModel {

    private DatabaseRepository databaseRepository;
    private PreferencesRepository preferencesRepository;
    private RoomsRepository roomsRepository;
   // private Device deviceId;
    private String deviceId;

    public PreferencesViewModel(@NonNull Application application) {
        super(application);
        databaseRepository = DatabaseRepository.getInstance(application);
        roomsRepository = RoomsRepository.getInstance();
        preferencesRepository = PreferencesRepository.getInstance();
    }

    public LiveData< List< Preferences > > getAllPreferences() {
        return databaseRepository.getAllPreferences();
    }

    public void insert(final Preferences preferences) {
        databaseRepository.insert(preferences);
    }

    public void update(final Preferences preferences) {
        databaseRepository.update(preferences);
    }

    public void showPrefrences(String deviceId) {
        preferencesRepository.showPreferences(deviceId);
    }

    public void updatePreferences(Preferences preference) {
        preferencesRepository.updatePrefrences(preference);
    }

    public LiveData< List< Preferences > > getPrefrences() {
        return databaseRepository.getAllPreferences();
    }

   public LiveData<Preferences> getLastPreference() {
        return preferencesRepository.getPreferences();
   }

    public void updateRooms() {
        roomsRepository.updateRooms();
    }

    public LiveData<List< Device >> getDevices() {
        return roomsRepository.getList();
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {

        return deviceId;
    }
}
