package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.Repositories.DatabaseRepository;
import com.example.sep4androidapp.Repositories.RoomsRepository;
import com.example.sep4androidapp.Repositories.SetUpDeviceRepository;

import java.util.List;

public class RoomsViewModel extends AndroidViewModel {
    private RoomsRepository roomsRepository;
    private SetUpDeviceRepository setUpDeviceRepository;
    private DatabaseRepository databaseRepository;

    public RoomsViewModel(@NonNull Application application) {
        super(application);
        roomsRepository = RoomsRepository.getInstance();
        setUpDeviceRepository = SetUpDeviceRepository.getInstance();
        databaseRepository = DatabaseRepository.getInstance(application);
    }

    public void deleteDeviceFromDb(NewDeviceModel device)
    {
        databaseRepository.deleteDevice(device);
    }

    public LiveData<List<Device>> getDevices()
    {
        return roomsRepository.getList();
    }

    public void updateRooms(){
        roomsRepository.updateRooms();
    }

    public void deleteDevice(String deviceId)
    {
        roomsRepository.deleteDevice(deviceId);
    }

    public void insertDevice(NewDeviceModel model) {
        databaseRepository.insertDevice(model);
    }

    public void postDevice(NewDeviceModel model)
    {
        setUpDeviceRepository.postNewDevice(model);
    }


}
