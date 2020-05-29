package com.example.sep4androidapp.ViewModels;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4androidapp.Adapter.FactAdapter;
import com.example.sep4androidapp.Entities.Fact;
import com.example.sep4androidapp.Repositories.FactRepository;

import java.util.List;

public class FactViewModel extends ViewModel {
    private FactRepository repository;

    public FactViewModel() {
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
