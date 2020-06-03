package com.example.sep4androidapp.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Repositories.PreferencesRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;

import java.util.List;

public class PreferencesViewModel extends AndroidViewModel {

    private PreferencesRepository preferencesRepository;
    private RoomsRepository roomsRepository;
   // private Device deviceId;
    private String deviceId;

    public PreferencesViewModel(@NonNull Application application) {
        super(application);
        preferencesRepository = PreferencesRepository.getInstance(application);
        roomsRepository = RoomsRepository.getInstance();
    }

    public void deleteAllPreferences(){
        preferencesRepository.deleteAllPReferences();
    }

    public void deleteAllDevices(){
        preferencesRepository.deleteAllDevices();
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

    public void updatePreferences(Preferences preference) {
        preferencesRepository.updatePrefrences(preference);
    }

    public void insertDevice(final NewDeviceModel model){
        preferencesRepository.insertDevice(model);
    }

    public Preferences getPreferencesById(String deviceId){
        return preferencesRepository.getPreferencesById(deviceId);
    }

    public Preferences getPreference(){
        return preferencesRepository.getPreference();
    }

    public LiveData<List<NewDeviceModel>> getAllDevices(){
        return preferencesRepository.getAllDevices();
    }
    public LiveData<List<NewDeviceModel>> getDeviceLocal(){
        return preferencesRepository.getAllDevices();
    }

    public LiveData< List< Preferences > > getPrefrences() {
        return preferencesRepository.getAllPreferences();
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
