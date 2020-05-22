package com.example.sep4androidapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Entities.RoomCondition;
import com.example.sep4androidapp.Entities.StartStop;
import com.example.sep4androidapp.Repositories.ReportRepository;
import com.example.sep4androidapp.Repositories.StartStopRepository;

public class StatStopViewModel extends ViewModel {
    StartStopRepository repository;

    public StatStopViewModel() {

        repository = StartStopRepository.getInstance();
    }

    public LiveData<StartStop> getstarStop() {

        return repository.getStartStop();
    }

    public void start(){
        repository.start();
    }

    public void stop() {
        repository.stop();
    }
}
