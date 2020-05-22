package com.example.sep4androidapp.ViewModels;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Repositories.ReportRepository;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ReportViewModel extends ViewModel {

    private ReportRepository repository;
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    public ReportViewModel() {

        repository = ReportRepository.getInstance();
    }

    public LiveData<RoomCondition> getRoomCondition() {

       return repository.getRoomCondition();
    }

    public void updateRoomCondition(){
        repository.updateRoomCondition();
    }

    public void update() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(()-> {
                        Log.i("TAG", String.valueOf(Calendar.getInstance().getTime()));
                        updateRoomCondition();
                });
            }
        }, 0, 1000);
    }
}
