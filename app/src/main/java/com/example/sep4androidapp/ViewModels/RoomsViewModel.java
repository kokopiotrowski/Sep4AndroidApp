package com.example.sep4androidapp.ViewModels;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Adapter.RoomsAdapter;
import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Repositories.RoomsRepository;
import com.example.sep4androidapp.connection.responses.DeviceResponse;
import com.example.sep4androidapp.fragments.roomFragment.DeviceOverview;

import java.util.List;

public class RoomsViewModel extends ViewModel {
    private RoomsRepository repository;
    private DeviceOverview deviceOverview = new DeviceOverview();

    public RoomsViewModel() {
        repository = RoomsRepository.getInstance();
    }

    public LiveData<List<Device>> getDevices()
    {
        return repository.getList();
    }

    public void updateRooms(){
        repository.updateRooms();
    }


    public void openDevice(RoomsAdapter adapter, FragmentActivity activity) {
        adapter.setOnItemClickListener(device -> {
            Log.i("TAG", "Clicked item: " + device.getName());
            Bundle args = new Bundle();
            args.putString("deviceId", device.getDeviceId());
            args.putString("name", device.getName());
            deviceOverview.setArguments(args);
        deviceOverview.show(activity.getSupportFragmentManager(), "Overview");
        });
    }

}
