package com.example.sep4androidapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Repositiories.PreferencesRoomRepository;

import java.util.List;

public class PreferencesViewModel extends AndroidViewModel {

    private PreferencesRoomRepository preferencesRoomRepository;

    public PreferencesViewModel(@NonNull Application application) {
        super(application);
        preferencesRoomRepository = PreferencesRoomRepository.getInstance(application);
    }

    public LiveData<List<Preferences>> getAllPreferences(){
        return preferencesRoomRepository.getAllPreferences();
    }

    public void insert(final Preferences preferences){
        preferencesRoomRepository.insert(preferences);
    }

    public void update(final Preferences preferences){
        preferencesRoomRepository.update(preferences);
    }

    public void deletePreference(final Preferences preferences){
        preferencesRoomRepository.deletePreference(preferences);
    }

    public void deleteAllPreferences(){
         preferencesRoomRepository.deleteAllPreferences();
    }
}
