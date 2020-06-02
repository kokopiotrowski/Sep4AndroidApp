package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.Repositories.ReportRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ChartsReportViewModel extends AndroidViewModel {
    ReportRepository repository;
    private String deviceId;

    public ChartsReportViewModel(@NonNull Application application) {
        super(application);
        repository = ReportRepository.getInstance();
    }

    public LiveData<List<SleepSession>> getSleepSessions(){
        return repository.getSleepSessions();
    }

    public LiveData<List<Device>> getDevices() {
        repository.updateDevicesList("account_1");
        return repository.getDevices();}

    public void updateSleepSessions()
    {
        repository.updateSleepSessions(deviceId);
    }

    public void rateSleep(int sleepId, int rating) { repository.rateSleep(sleepId, rating);}


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}
