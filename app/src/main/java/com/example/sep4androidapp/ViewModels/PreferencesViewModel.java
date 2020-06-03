package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Repositories.DatabaseRepository;
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
        preferencesRepository = PreferencesRepository.getInstance(application);
        roomsRepository = RoomsRepository.getInstance();
        databaseRepository = DatabaseRepository.getInstance(application);
    }

    public void deleteAllPreferences() {
        databaseRepository.deleteAllPReferences();
    }

    public void deleteAllDevices() {
        databaseRepository.deleteAllDevices();
    }

    public LiveData<List<Preferences>> getAllPreferences() {
        return databaseRepository.getAllPreferences();
    }

    public void showPreferences(String deviceId) {
        preferencesRepository.showPreferences(deviceId);
    }

    public void savePreferencesToNetwork(Preferences preference) {
        preferencesRepository.savePreferencesToNetwork(preference);
    }

    public void insertDevice(final NewDeviceModel model) {
        databaseRepository.insertDevice(model);
    }

    public Preferences getPreferencesById(String deviceId) {
        return databaseRepository.getPreferencesById(deviceId);
    }

    public Preferences getPreference() {
        return databaseRepository.getPreference();
    }

    public LiveData<List<NewDeviceModel>> getAllDevices() {
        return databaseRepository.getAllDevices();
    }

    public LiveData<List<NewDeviceModel>> getDeviceLocal() {
        return databaseRepository.getAllDevices();
    }

    public LiveData<List<Preferences>> getPrefrences() {
        return databaseRepository.getAllPreferences();
    }

    public LiveData<Preferences> getLastPreference() {

        return preferencesRepository.getPreferences();
    }

    public void updateRooms() {
        roomsRepository.updateRooms();
    }

    public LiveData<List<Device>> getDevices() {

        return roomsRepository.getList();
    }


    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {

        return deviceId;
    }
}
