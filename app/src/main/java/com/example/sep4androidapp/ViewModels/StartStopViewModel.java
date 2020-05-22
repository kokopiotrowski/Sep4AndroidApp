package com.example.sep4androidapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Entities.StartStop;
import com.example.sep4androidapp.Repositories.StartStopRepository;

public class StartStopViewModel extends ViewModel {
    private StartStopRepository repository;

    public StartStopViewModel() {
        repository = StartStopRepository.getInstance();
    }

    public void start() {
        repository.start();
    }

    public void stop() {
        repository.stop();
    }

    public void receiveStatus() {
        repository.receiveStatus();
    }

    public MutableLiveData<Boolean> getStatus() {
       return repository.getStatus();
    }


}
