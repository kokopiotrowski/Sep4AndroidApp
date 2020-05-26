package com.example.sep4androidapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Repositories.RoomsRepository;
import com.example.sep4androidapp.connection.responses.DeviceResponse;

import java.util.List;

public class RoomsViewModel extends ViewModel {
    private RoomsRepository repository;

    public RoomsViewModel() {
        repository = RoomsRepository.getInstance();
    }

    public LiveData<List<DeviceResponse>> getDevices()
    {
        return repository.getList();
    }

    public void updateRooms(){
        repository.updateRooms();
    }
}
