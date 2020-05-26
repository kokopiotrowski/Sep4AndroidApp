package com.example.sep4androidapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4androidapp.Entities.Preferences;
import com.example.sep4androidapp.Repositories.PreferencesRoomRepository;

import java.util.List;

public class PrefrencesViewModel extends AndroidViewModel {

    private PreferencesRoomRepository preferencesRoomRepository;

    public PrefrencesViewModel(@NonNull Application application) {
        super(application);
        preferencesRoomRepository = PreferencesRoomRepository.getInstance(application);
    }

    public LiveData< List< Preferences > > getAllPreferences() {
        return preferencesRoomRepository.getAllPreferences();
    }

    public void insert(final Preferences preferences) {
        preferencesRoomRepository.insert(preferences);
    }

    public void update(final Preferences preferences) {
        preferencesRoomRepository.update(preferences);
    }

    public void deletePreference(final Preferences preferences) {
        preferencesRoomRepository.deletePreference(preferences);
    }

    public void deleteAllPreferences() {
        preferencesRoomRepository.deleteAllPreferences();
    }

    //---------------------------------------------------------------------------

    public void showPrefrences() {
        preferencesRoomRepository.showPreferences();
    }

    public void updatePrefrences(Preferences preference) {
        preferencesRoomRepository.updatePrefrences(preference);
    }

    public LiveData< List< Preferences > > getPrefrences() {
        return preferencesRoomRepository.getAllPreferences();
    }

    public LiveData<Preferences> getLastPreference(){
        return preferencesRoomRepository.getPreFrence();
    }
}
