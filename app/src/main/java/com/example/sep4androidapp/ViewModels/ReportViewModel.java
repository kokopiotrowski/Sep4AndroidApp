package com.example.sep4androidapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Repositories.ReportRepository;

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
