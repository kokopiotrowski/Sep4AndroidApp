package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Repositories.PreferencesRepository;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;

import java.util.List;

public class PrefrencesViewModel extends AndroidViewModel {

    private PreferencesRepository preferencesRepository;
    private RoomsRepository roomsRepository;
   // private Device deviceId;
    private String deviceId;

    public PrefrencesViewModel(@NonNull Application application) {
        super(application);
        preferencesRepository = PreferencesRepository.getInstance(application);
        roomsRepository = RoomsRepository.getInstance();
    }

    public LiveData< List< Preferences > > getAllPreferences() {
        return preferencesRepository.getAllPreferences();
    }

    public void insert(final Preferences preferences) {
        preferencesRepository.insert(preferences);
    }

    public void update(final Preferences preferences) {
        preferencesRepository.update(preferences);
    }

    public void showPrefrences(String deviceId) {
        preferencesRepository.showPreferences(deviceId);
    }

    public void updatePrefrences(Preferences preference) {
        preferencesRepository.updatePrefrences(preference);
    }

    public LiveData< List< Preferences > > getPrefrences() {
        return preferencesRepository.getAllPreferences();
    }

   public LiveData<Preferences> getLastPreference() {

        return preferencesRepository.getPre();
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

/*
    public void setDeviceId(String devId) {
        deviceId.setDeviceId(devId);
    }

    public String getDeviceId() {

        return deviceId.getDeviceId();
    }

 */



}
