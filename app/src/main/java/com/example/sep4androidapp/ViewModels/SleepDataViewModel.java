package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.SleepData;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;

import java.util.List;

public class SleepDataViewModel extends AndroidViewModel {
    private ReportRepository reportRepository;
    private RoomsRepository roomsRepository;

    public SleepDataViewModel(@NonNull Application application) {
        super(application);

        reportRepository = ReportRepository.getInstance();
        roomsRepository = RoomsRepository.getInstance();
    }

    public LiveData<List<Device>> getDevices() {
        return roomsRepository.getList();
    }

    public void updateSleepSessions(String deviceId){

        reportRepository.updateSleepSessions(deviceId);

    }

    public LiveData<List<SleepSession>> getSleepSessions()
    {
        return reportRepository.getSleepSessions();
    }

    public LiveData<SleepData> getSleepData()
    {
        return reportRepository.getSleepData();
    }

    public void updateSleepData(int sleepId){

        reportRepository.updateSleepData(sleepId);

    }

    public void updateDevices(){

        roomsRepository.updateRooms();
    }
}
