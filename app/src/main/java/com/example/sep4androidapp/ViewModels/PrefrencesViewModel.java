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

    private PreferencesRepository preferencesRoomRepository;

    //extra for spinner
    private ReportRepository reportRepository;
    private RoomsRepository roomsRepository;
    private String deviceId;




    public PrefrencesViewModel(@NonNull Application application) {
        super(application);
        preferencesRoomRepository = PreferencesRepository.getInstance(application);

        // extra for spinner
        reportRepository = ReportRepository.getInstance();
        roomsRepository = RoomsRepository.getInstance();
    }

    public LiveData< List< Preferences > > getAllPreferences() {
        return preferencesRoomRepository.getAllPreferences();
    }

    public void insert(final Preferences preferences) {
        preferencesRoomRepository.insert(preferences);
    }

    public void update(final Preferences preferences) {
        preferencesRoomRepository.update(preferences);
    }
//
//    public void deletePreference(final Preferences preferences) {
//        preferencesRoomRepository.deletePreference(preferences);
//    }
//
//    public void deleteAllPreferences() {
//        preferencesRoomRepository.deleteAllPreferences();
//    }
//
//    //---------------------------------------------------------------------------
//
    public void showPrefrences(String deviceId) {
        preferencesRoomRepository.showPreferences(deviceId);
    }

    public void updatePrefrences(Preferences preference) {
        preferencesRoomRepository.updatePrefrences(preference);
    }

    public LiveData< List< Preferences > > getPrefrences() {
        return preferencesRoomRepository.getAllPreferences();
    }

   public LiveData<Preferences> getLastPreference() {

        return preferencesRoomRepository.getPre();
   }




   // extra for spinner

    public LiveData< RoomCondition > getRoomCondition() {

        return reportRepository.getRoomCondition();
    }

    public void updateRoomCondition(String deviceId) {
        reportRepository.updateRoomCondition(deviceId);
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
