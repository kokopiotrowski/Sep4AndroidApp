package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sep4androidapp.Repositories.StartStopRepository;
import com.example.sep4androidapp.connection.apis.ApiCallBack;

public class StartStopViewModel extends AndroidViewModel {
    private StartStopRepository repository;

    public StartStopViewModel(@NonNull Application application) {
        super(application);
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
}
