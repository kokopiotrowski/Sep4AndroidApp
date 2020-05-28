package com.example.sep4androidapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Repositories.SetUpDeviceRepository;

import java.util.List;

public class SetUpDeviceViewModel extends ViewModel {
    private SetUpDeviceRepository repository;

    public SetUpDeviceViewModel() {
        repository = SetUpDeviceRepository.getInstance();
    }

    public void updateAvailableDevices()
    {
        repository.updateAvailableDevices();
    }

    public LiveData<List<String>> getAvailableDevices()
    {
        return repository.getAvailableDevices();
    }
}
