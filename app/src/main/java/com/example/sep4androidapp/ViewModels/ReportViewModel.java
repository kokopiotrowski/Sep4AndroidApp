package com.example.sep4androidapp.ViewModels;

import android.widget.RadioButton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.github.mikephil.charting.charts.HorizontalBarChart;

public class ReportViewModel extends ViewModel {

    ReportRepository repository;

    public ReportViewModel() {



        repository = ReportRepository.getInstance();
    }

    public LiveData<RoomCondition> getRoomCondition() {

       return repository.getRoomCondition();
    }

    public void updateRoomCondition(){
        repository.updateRoomCondition();
    }




}
