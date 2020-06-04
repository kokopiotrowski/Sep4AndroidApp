package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.Repositories.PreferencesRepository;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;

import java.util.List;

public class Co2FragmentViewModel extends AndroidViewModel {
    private RoomsRepository roomsRepository;
    private PreferencesRepository preferencesRepository;
    private ReportRepository reportRepository;

    public Co2FragmentViewModel(@NonNull Application application) {
        super(application);
        roomsRepository = RoomsRepository.getInstance();
        preferencesRepository = PreferencesRepository.getInstance(application);
        reportRepository = ReportRepository.getInstance();
    }

    public LiveData<String> getChosenDeviceId(){
        return roomsRepository.getChosenDeviceId();
    }


    public LiveData<Preferences> getPreferences()
    {
        return preferencesRepository.getPreferences();
    }

    public LiveData<List<SleepSession>> getSleepSessionsDaily(){
        return reportRepository.getSleepSessionsDaily();
    }
    public LiveData<List<SleepSession>> getSleepSessionsWeekly(){
        return reportRepository.getSleepSessionsWeekly();
    }
    public LiveData<List<SleepSession>> getSleepSessionsMonthly(){
        return reportRepository.getSleepSessionsMonthly();
    }

    public LiveData<List<Device>> getDevicesForFragments() {
        return roomsRepository.getListForFragments();
    }

}
