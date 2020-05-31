package com.example.sep4androidapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Repositories.RoomsRepository;
import com.example.sep4androidapp.Repositories.SetUpDeviceRepository;

import java.util.List;

public class RoomsViewModel extends ViewModel {
    private RoomsRepository repository;
    private SetUpDeviceRepository setUpDeviceRepository;

    public RoomsViewModel() {
        repository = RoomsRepository.getInstance();
        setUpDeviceRepository = SetUpDeviceRepository.getInstance();
    }

    public LiveData<List<Device>> getDevices()
    {
        return repository.getList();
    }

    public void updateRooms(){
        repository.updateRooms();
    }

    public void deleteDevice(String deviceId)
    {
        repository.deleteDevice(deviceId);
    }

    public void postDevice(NewDeviceModel model)
    {
        setUpDeviceRepository.postNewDevice(model);
    }


}
