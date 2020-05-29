package com.example.sep4androidapp.ViewModels;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Repositories.FactRepository;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;
import com.example.sep4androidapp.Repositories.StartStopRepository;
import com.example.sep4androidapp.connection.ApiCallBack;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentFirstPageViewModel extends ViewModel {
    private ReportRepository reportRepository;
    private StartStopRepository startStopRepository;
    private RoomsRepository roomsRepository;
    private FactRepository factRepository;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public FragmentFirstPageViewModel() {

        reportRepository = ReportRepository.getInstance();
        startStopRepository = StartStopRepository.getInstance();
        roomsRepository = RoomsRepository.getInstance();
        factRepository = FactRepository.getInstance();
    }

    public LiveData<RoomCondition> getRoomCondition() {

        return reportRepository.getRoomCondition();
    }

    public void updateRoomCondition(String deviceId) {
        reportRepository.updateRoomCondition(deviceId);
    }

    public void update(String deviceId) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    Log.i("TIMINGG", String.valueOf(Calendar.getInstance().getTime()));
                    updateRoomCondition(deviceId);
                });
            }
        }, 0, 1000);
    }

    public void stopTimer() {
        timer.cancel();
    }

    public void start(String deviceId) {
        startStopRepository.start(deviceId);
    }

    public void stop(String deviceId) {
        startStopRepository.stop(deviceId);
    }

    public void receiveStatus(String deviceId, ApiCallBack callBack) {
        startStopRepository.receiveStatus(deviceId, callBack);
    }

    public LiveData<List<Device>> getDevices() {
        return roomsRepository.getList();
    }

    public void updateRooms() {
        roomsRepository.updateRooms();
    }

    public void switchCheck(boolean isChecked) {
        if (isChecked) {
            start(getDeviceId());
            update(getDeviceId());
        } else {
            stop(getDeviceId());
            stopTimer();
        }
    }

    public void getFactRandomly()
    {
        factRepository.getFactRandomly();
    }

    public LiveData<Fact> getFact()
    {
        return factRepository.getFact();
    }
}
