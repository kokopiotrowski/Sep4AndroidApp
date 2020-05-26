package com.example.sep4androidapp.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.connection.AccountDevicesApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.DeviceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomsRepository {
    private static RoomsRepository instance;
    private MutableLiveData<List<DeviceResponse>> list;

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
        Call<List<DeviceResponse>> call = api.getDevices();
        call.enqueue(new Callback<List<DeviceResponse>>() {
            @Override
            public void onResponse(Call<List<DeviceResponse>> call, Response<List<DeviceResponse>> response) {
                if(response.code() == 200)
                {
//                    list.setValue();
                }
                Log.i("RESPONSECODE", "Message: " + response.code());
            }

            @Override
            public void onFailure(Call<List<DeviceResponse>> call, Throwable t) {
                Log.i("Why", "" + t.getCause());
            }
        });
    }

    public LiveData<List<DeviceResponse>> getList(){
        return list;
    }

}
