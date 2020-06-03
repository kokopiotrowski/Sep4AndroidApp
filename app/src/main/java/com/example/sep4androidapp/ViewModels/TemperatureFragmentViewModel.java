package com.example.sep4androidapp.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.Repositories.PreferencesRepository;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TemperatureFragmentViewModel extends AndroidViewModel {

    private RoomsRepository roomsRepository;
    private PreferencesRepository preferencesRepository;
    private ReportRepository reportRepository;

    public TemperatureFragmentViewModel(@NonNull Application application) {
        super(application);
        roomsRepository = RoomsRepository.getInstance();
        preferencesRepository = PreferencesRepository.getInstance(application);
        reportRepository = ReportRepository.getInstance();
    }

    public LiveData<String> getChosenDeviceId(){

        return roomsRepository.getChosenDeviceId();

    }


    public void updateMonthlySleepSessions(String deviceId){
        LocalDate today = LocalDate.now();
        LocalDate monthAgo = today.minusMonths(1);
        today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        monthAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    reportRepository.updateMonthlySleepSessions(deviceId, today.toString(), monthAgo.toString());
    }


    public void updateWeeklySleepSessions(String deviceId){
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusWeeks(1);
        today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        weekAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        reportRepository.updateWeeklySleepSessions(deviceId, today.toString(), weekAgo.toString());
    }


    public void updateDailySleepSessions(String deviceId){
        LocalDate today = LocalDate.now();
        LocalDate dayAgo = today.minusDays(1);
        today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dayAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        reportRepository.updateDailySleepSessions(deviceId, today.toString(), dayAgo.toString());
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


}
