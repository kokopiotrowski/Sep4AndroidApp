package com.example.sep4androidapp.Repositories;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.connection.FactApi;
import com.example.sep4androidapp.connection.RoomConditionApi;
import com.example.sep4androidapp.connection.ServiceGenerator;
import com.example.sep4androidapp.connection.responses.FactResponse;
import com.example.sep4androidapp.connection.responses.RoomConditionResponse;
import com.example.sep4androidapp.fragments.mainFragment.mainViewFragments.FragmentFirstPage;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


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
                }
                Log.i("FactRepo", "Pouneh1 " + response.code());
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onFailure(Call<List<Fact>> call, Throwable t) {
                Log.i("factRepo", "Pouneh2" + t.getMessage());
            }
        });
    }

    public LiveData<List<Fact>> getFactList() {
        return Factlist;
    }


    public void getFactRandomly() {

        FactApi factApi = ServiceGenerator.getFactApi();
        Call<FactResponse> call = factApi.getRandomFact();
        call.enqueue(new Callback<FactResponse>() {
            @Override
            public void onResponse(Call<FactResponse> call, Response<FactResponse> response) {
                if (response.code() == 200) {

                    fact.setValue(response.body().getFact());


                    Log.i("RandomFact", "Pouneh0" + response.code());

                } else {
                    Log.i("RandomFact", "Pouneh1 " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FactResponse> call, Throwable t) {
                Log.i("RandomFact", t.getMessage());

            }
        });

    }

    public LiveData<Fact> getFact() {
        return fact;
    }
}
