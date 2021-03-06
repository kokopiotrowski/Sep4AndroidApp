package com.example.sep4androidapp.ViewModels;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Repositories.DatabaseRepository;
import com.example.sep4androidapp.Repositories.FactRepository;
import com.example.sep4androidapp.Repositories.PreferencesRepository;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;
import com.example.sep4androidapp.Repositories.StartStopRepository;
import com.example.sep4androidapp.connection.apis.ApiCallBack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentFirstPageViewModel extends AndroidViewModel {
    private ReportRepository reportRepository;
    private StartStopRepository startStopRepository;
    private RoomsRepository roomsRepository;
    private FactRepository factRepository;
    private PreferencesRepository preferencesRepository;
    private DatabaseRepository databaseRepository;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private String deviceId;

    public FragmentFirstPageViewModel(@NonNull Application application) {
        super(application);
        reportRepository = ReportRepository.getInstance();
        startStopRepository = StartStopRepository.getInstance();
        roomsRepository = RoomsRepository.getInstance();
        factRepository = FactRepository.getInstance();
        preferencesRepository = PreferencesRepository.getInstance(application);
        databaseRepository = DatabaseRepository.getInstance(application);
    }

    public void setChosenDeviceId(String deviceId) {
        this.deviceId = deviceId;
        roomsRepository.setChosenDeviceId(deviceId);
    }

    public Preferences getPreferencesById(String deviceId) {
        return databaseRepository.getPreferencesById(deviceId);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public LiveData<RoomCondition> getRoomCondition() {

        return reportRepository.getRoomCondition();
    }

    public void updateRoomCondition(String deviceId) {
        reportRepository.updateRoomCondition(deviceId);
    }

    public void update() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    Log.i("TIMER", String.valueOf(Calendar.getInstance().getTime()));
                    updateRoomCondition(getDeviceId());
                });
            }
        }, 0, 4000);
    }

    public void stopTimer() {
        timer.cancel();
    }

    private void start(String deviceId) {
        startStopRepository.start(deviceId);
    }

    private void stop(String deviceId) {
        startStopRepository.stop(deviceId);
    }

    public void receiveStatus(String deviceId, ApiCallBack callBack) {
        startStopRepository.receiveStatus(deviceId, callBack);
    }

    public LiveData<List<Device>> getDevicesFromApi() {
        return roomsRepository.getList();
    }

    public void updateRooms() {
        roomsRepository.updateRooms();
    }

    public void switchCheck(boolean isChecked) {
        if (isChecked) {
            start(getDeviceId());
            update();
        } else {
            stop(getDeviceId());
            stopTimer();
        }
    }

    public void getFactRandomly() {
        factRepository.getFactRandomly();
    }

    public LiveData<Fact> getFact() {
        return factRepository.getFact();
    }

    public void showPreferences(String deviceId) {
        preferencesRepository.showPreferences(deviceId);
    }

    public LiveData<Preferences> getPreferencesFromApi() {
        return preferencesRepository.getPreferences();
    }

    public LiveData<List<NewDeviceModel>> getAllLocalDevices() {
        return databaseRepository.getAllDevices();
    }

    public void updateMonthlySleepSessions(String deviceId) {
        LocalDate today = LocalDate.now();
        LocalDate monthAgo = today.minusMonths(1);
        today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        monthAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        reportRepository.updateMonthlySleepSessions(deviceId, today.toString(), monthAgo.toString());
    }

    public void updateWeeklySleepSessions(String deviceId) {
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusWeeks(1);
        today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        weekAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        reportRepository.updateWeeklySleepSessions(deviceId, today.toString(), weekAgo.toString());
    }

    public void updateDailySleepSessions(String deviceId) {
        LocalDate today = LocalDate.now();
        LocalDate dayAgo = today.minusDays(1);
        today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dayAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        reportRepository.updateDailySleepSessions(deviceId, today.toString(), dayAgo.toString());
    }

    public void updateRoomsForFragments() {
        roomsRepository.updateRoomsForFragments();
    }
}
