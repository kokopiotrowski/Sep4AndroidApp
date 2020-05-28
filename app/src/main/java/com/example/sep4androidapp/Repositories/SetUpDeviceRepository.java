package com.example.sep4androidapp.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.connection.AccountDevicesApi;
import com.example.sep4androidapp.connection.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetUpDeviceRepository {
    private static SetUpDeviceRepository instance;
    private MutableLiveData<List<String>> availableDevicesList;

    public SetUpDeviceRepository() {
        availableDevicesList = new MutableLiveData<>();
    }

    public static synchronized SetUpDeviceRepository getInstance()
    {
        if(instance == null)
        {
            instance = new SetUpDeviceRepository();
        }
        return instance;
    }

    public void updateAvailableDevices()
    {
        AccountDevicesApi devicesApi = ServiceGenerator.getAccountDevicesApi();
        Call<List<String>> call = devicesApi.getAvailableDevices();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Log.i("SetUpRepo", "Update response: " + response.code() + " List size: " +response.body().size());
                if(response.code() == 200)
                {
                    availableDevicesList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.i("SetUpRepo", "Failing update-response: " + t.getMessage());

            }
        });
    }

    public LiveData<List<String>> getAvailableDevices()
    {
        return availableDevicesList;

    }

    public void postNewDevice(NewDeviceModel model) {
        Log.i("TAG", "Device id: " + model.getDeviceId() + " Name: " + model.getName());

        AccountDevicesApi devicesApi = ServiceGenerator.getAccountDevicesApi();
        Call<Device> call = devicesApi.addDevice(model);
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                Log.i("SetUpRepo", "Post device response: " + response.code() + " Device id: " +response.body().getDeviceId());

            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Log.i("SetUpRepo", "Failing with posting device: " + t.getMessage());

            }
        });
    }
}
