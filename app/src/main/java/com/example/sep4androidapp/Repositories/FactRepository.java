package com.example.sep4androidapp.Repositories;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.connection.apis.FactApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.FactResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FactRepository {
    private static FactRepository instance;
    private MutableLiveData<List<Fact>> Factlist;
    private MutableLiveData<Fact> fact;

    private FactRepository() {
        Factlist = new MutableLiveData<>();
        fact = new MutableLiveData<>();
    }

    public static synchronized FactRepository getInstance() {
        if (instance == null) {
            instance = new FactRepository();
        }
        return instance;
    }

    public void updateFacts() {
        FactApi factApi = ServiceGenerator.getFactApi();
        Call<List<Fact>> call = factApi.getFact();
        call.enqueue(new Callback<List<Fact>>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call<List<Fact>> call, Response<List<Fact>> response) {
                if (response.code() == 200) {
                    Factlist.setValue(response.body());
                }else {
                    Log.i("FactRepo", "Response code received on updateFacts: " + response.code());
                }
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onFailure(Call<List<Fact>> call, Throwable t) {
                Log.i("FactRepo", "Failure at updateFacts: " + t.getMessage());
            }
        });
    }

    public void getFactRandomly() {
        FactApi factApi = ServiceGenerator.getFactApi();
        Call<FactResponse> call = factApi.getRandomFact();
        call.enqueue(new Callback<FactResponse>() {
            @Override
            public void onResponse(Call<FactResponse> call, Response<FactResponse> response) {
                if (response.code() == 200) {
                    fact.setValue(response.body().getFact());
                }else {
                    Log.i("FactRepo", "Response code received on getFactRandomly: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FactResponse> call, Throwable t) {
                Log.i("FactRepo", "Failure at getFactRandomly: " + t.getMessage());
            }
        });
    }

    public LiveData<List<Fact>> getFactList() {
        return Factlist;
    }

    public LiveData<Fact> getFact() {
        return fact;
    }
}
