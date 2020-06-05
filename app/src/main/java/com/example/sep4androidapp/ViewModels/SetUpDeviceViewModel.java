package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Repositories.SetUpDeviceRepository;

import java.util.List;

public class SetUpDeviceViewModel extends AndroidViewModel {
    private SetUpDeviceRepository repository;

    public SetUpDeviceViewModel(@NonNull Application application) {
        super(application);
        repository = SetUpDeviceRepository.getInstance();
    }

    public void updateAvailableDevices() {
        repository.updateAvailableDevices();
    }

    public LiveData<List<String>> getAvailableDevices() {
        return repository.getAvailableDevices();
    }

    public LiveData<String> getMessage() {
        return repository.getMessage();
    }

    public void postNewDevice(NewDeviceModel model) {
        repository.postNewDevice(model);
    }
}
