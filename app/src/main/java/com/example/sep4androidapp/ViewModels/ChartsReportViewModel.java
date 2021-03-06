package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.IdealRoomConditions;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ChartsReportViewModel extends AndroidViewModel {
    ReportRepository repository;
    RoomsRepository roomsRepository;
    private String deviceId;

    public ChartsReportViewModel(@NonNull Application application) {
        super(application);
        repository = ReportRepository.getInstance();
        roomsRepository = RoomsRepository.getInstance();
    }

    public void updateSleepSessionsAndIdealRoomConditions()
    {
        if(deviceId != null && deviceId!= "") {
            repository.updateSleepSessions(deviceId);
            repository.updateIdealConditions(deviceId);
        }
    }

    public void rateSleep(int sleepId, int rating) { repository.rateSleep(sleepId, rating);}

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public LiveData<List<SleepSession>> getSleepSessions(){
        return repository.getSleepSessions();
    }

    public LiveData<List<Device>> getDevices() {
        roomsRepository.updateRooms();
        return roomsRepository.getList();
    }

    public LiveData<IdealRoomConditions> getIdealRoomConditions() {
        return repository.getIdealRoomConditions();
    }
}
