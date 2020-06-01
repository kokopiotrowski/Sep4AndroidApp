package com.example.sep4androidapp.ViewModels;

import android.app.Application;
import android.os.Handler;
import android.util.Log;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
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

public class FragmentFirstPageViewModel extends AndroidViewModel {
    private ReportRepository reportRepository;
    private StartStopRepository startStopRepository;
    private RoomsRepository roomsRepository;
    private FactRepository factRepository;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private String deviceId;

    public FragmentFirstPageViewModel(@NonNull Application application) {
        super(application);
        reportRepository = ReportRepository.getInstance();
        startStopRepository = StartStopRepository.getInstance();
        roomsRepository = RoomsRepository.getInstance();
        factRepository = FactRepository.getInstance();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }



    public LiveData<RoomCondition> getRoomCondition() {

        return reportRepository.getRoomCondition();
    }

    private void updateRoomCondition(String deviceId) {
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

    public LiveData<List<Device>> getDevices() {
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

    public void getFactRandomly()
    {
        factRepository.getFactRandomly();
    }

    public LiveData<Fact> getFact()
    {
        return factRepository.getFact();
    }
}
