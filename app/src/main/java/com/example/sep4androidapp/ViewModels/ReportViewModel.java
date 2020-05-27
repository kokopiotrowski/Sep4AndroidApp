package com.example.sep4androidapp.ViewModels;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.SleepData;
import com.example.sep4androidapp.Entities.SleepSession;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.connection.responses.SleepSessionResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReportViewModel extends ViewModel {

    private ReportRepository repository;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public ReportViewModel() {

        repository = ReportRepository.getInstance();
    }

    public LiveData<RoomCondition> getRoomCondition() {

       return repository.getRoomCondition();
    }

    public void updateRoomCondition(String deviceId){
        repository.updateRoomCondition(deviceId);
    }

    public void update(String deviceId) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(()-> {
                        Log.i("TIMINGG", String.valueOf(Calendar.getInstance().getTime()));
                        updateRoomCondition(deviceId);
                });
            }
        }, 0, 1000);
    }

    public void stopTimer()
    {
        timer.cancel();
    }

    public LiveData<SleepData> getSleepData() {
        return repository.getSleepData();
    }

    public void updateSleepData() {
        repository.updateSleepData();
    }

    public LiveData<List<SleepSession>> getSleepSessions(){
        return repository.getSleepSessions();
    }

    public void updateSleepSessions(int deviceId, LocalDate start, LocalDate end)
    {
        repository.updateSleepSessions(deviceId, start, end);
    }
}
