package com.example.sep4androidapp.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4androidapp.Entities.User;
import com.example.sep4androidapp.LocalStorage.AppDAO;
import com.example.sep4androidapp.LocalStorage.AppDatabase;

import java.util.List;

public class AccountRepository {
    private AppDAO appDAO;
    private static AccountRepository instance;
    private LiveData<List<User>> userData;
    private MutableLiveData<User> user;

    private AccountRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        appDAO = appDatabase.appDAO();
        user = new MutableLiveData<>();


    }

    public static synchronized AccountRepository getInstance(Application application) {
        if (instance == null)
            instance = new AccountRepository(application);
        return instance;
    }

  
}
