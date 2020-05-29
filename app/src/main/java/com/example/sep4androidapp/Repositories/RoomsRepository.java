package com.example.sep4androidapp.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.connection.AccountDevicesApi;
import com.example.sep4androidapp.connection.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomsRepository {
    private static RoomsRepository instance;
    private MutableLiveData<List<Device>> list;

    private RoomsRepository()
    {
        list = new MutableLiveData<>();
    }

    public static synchronized  RoomsRepository getInstance()
    {
        if(instance == null){
            instance = new RoomsRepository();
        }
        return instance;
    }

    public void updateRooms(){
        AccountDevicesApi api = ServiceGenerator.getAccountDevicesApi();
        Call<List<Device>> call = api.getDevices();
        call.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if(response.code() == 200)
                {
                    list.setValue(response.body());
                }
                Log.i("roomsRepo", "Update room: " + response.code());
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Log.i("roomsRepo", "Update room failed" + t.getCause());
            }
        });
    }

    public void deleteDevice(String deviceId)
    {
        AccountDevicesApi api = ServiceGenerator.getAccountDevicesApi();
        Call<Void> call = api.deleteDevice(deviceId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("roomsRepo", "Delete device succeeded" + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("roomsRepo", "Deleting device failed" + t.getCause());
            }
        });
        updateRooms();
    }

    public LiveData<List<Device>> getList(){
        return list;
    }
}
