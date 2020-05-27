package com.example.sep4androidapp.ViewModels;

import android.util.Log;

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

    public void start(String deviceId) {
        repository.start(deviceId);
    }

    public void stop(String deviceId) {
        repository.stop(deviceId);
    }

    public void receiveStatus(String deviceId) {
        repository.receiveStatus(deviceId);
    }

    public MutableLiveData<Boolean> getStatus() {
       return repository.getStatus();
    }


}
