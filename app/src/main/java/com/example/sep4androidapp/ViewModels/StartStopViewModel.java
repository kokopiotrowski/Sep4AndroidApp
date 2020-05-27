package com.example.sep4androidapp.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Repositories.StartStopRepository;
import com.example.sep4androidapp.connection.ApiCallBack;

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

    public void receiveStatus(String deviceId, ApiCallBack callBack) {
        repository.receiveStatus(deviceId, callBack);
    }

    public MutableLiveData<Boolean> getStatus() {
       return repository.getStatus();
    }


}
