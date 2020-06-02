package com.example.sep4androidapp.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Adapter.FactAdapter;
import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.Repositories.FactRepository;

import java.util.List;

public class FactViewModel extends AndroidViewModel {
    private FactRepository repository;

    public FactViewModel(@NonNull Application application) {
        super(application);
        repository = FactRepository.getInstance();
    }

    public LiveData< List< Fact > > getFacts()
    {
        return repository.getFactList();
    }

    public void updateFacts(){
        repository.updateFacts();
    }


}
