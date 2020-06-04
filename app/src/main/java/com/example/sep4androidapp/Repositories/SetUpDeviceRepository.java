package com.example.sep4androidapp.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Device;
import com.example.sep4androidapp.Entities.NewDeviceModel;
import com.example.sep4androidapp.connection.apis.AccountDevicesApi;
import com.example.sep4androidapp.connection.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetUpDeviceRepository {
    private static SetUpDeviceRepository instance;
    private RoomsRepository roomsRepository = RoomsRepository.getInstance();
    private MutableLiveData<List<String>> availableDevicesList;
    private MutableLiveData<String> message;

    public SetUpDeviceRepository() {
        availableDevicesList = new MutableLiveData<>();
        message = new MutableLiveData<>();
    }

    public static synchronized SetUpDeviceRepository getInstance() {
        if (instance == null) {
            instance = new SetUpDeviceRepository();
        }
        return instance;
    }

    public void updateAvailableDevices() {
        AccountDevicesApi devicesApi = ServiceGenerator.getAccountDevicesApi();
        Call<List<String>> call = devicesApi.getAvailableDevices();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.code() == 200) {
                    availableDevicesList.setValue(response.body());
                } else {
                    Log.i("SetUpRepo", "Response after updateAvailableDevices: " + response.code() + " List size: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.i("SetUpRepo", "Failing update-response: " + t.getMessage());
            }
        });
    }

    public void postNewDevice(NewDeviceModel model) {
        AccountDevicesApi devicesApi = ServiceGenerator.getAccountDevicesApi();
        Call<Device> call = devicesApi.addDevice(model);
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if (response.code() == 200) {
                    message.setValue("success");
                    roomsRepository.updateRooms();
                    message.setValue("");
                } else {
                    Log.i("SetUpRepo", "Error-response code received during posting: " + response.code());
                    if (response.code() == 403) {
                        message.setValue("This device is already associated with another user");
                    } else if (response.code() == 404) {
                        message.setValue("DeviceId is invalid");
                    } else if (response.code() == 406) {
                        message.setValue("This device is already associated with this user");
                    }
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Log.i("SetUpRepo", "Failing with posting device: " + t.getMessage());
            }
        });
    }

    public LiveData<List<String>> getAvailableDevices() {
        return availableDevicesList;
    }

    public LiveData<String> getMessage() {
        return message;
    }
}
