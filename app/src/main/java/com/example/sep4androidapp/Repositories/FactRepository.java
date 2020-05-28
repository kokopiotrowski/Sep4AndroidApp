package com.example.sep4androidapp.Repositories;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.connection.FactApi;
import com.example.sep4androidapp.connection.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FactRepository {
    private static FactRepository instance;
    private MutableLiveData< List< Fact > > Factlist;

    private FactRepository() {
        Factlist = new MutableLiveData<>();
    }

    public static synchronized FactRepository getInstance() {
        if (instance == null) {
            instance = new FactRepository();
        }
        return instance;
    }

    public void updateFacts() {
        FactApi factApi = ServiceGenerator.getFactApi();
        Call< List< Fact > > call = factApi.getFact();
        call.enqueue(new Callback< List< Fact > >() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call< List< Fact > > call, Response< List< Fact > > response) {
                if (response.code() == 200) {
                    Factlist.setValue(response.body());
                }
                Log.i("FactRepo", "Pouneh1 " + response.code());
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onFailure(Call< List< Fact > > call, Throwable t) {
                Log.i("factRepo", "Pouneh2" + t.getMessage());
            }
        });
    }

    public LiveData< List< Fact > > getFactList() {
        return Factlist;
    }


}
