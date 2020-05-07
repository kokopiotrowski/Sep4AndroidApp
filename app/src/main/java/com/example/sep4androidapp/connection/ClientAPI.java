package com.example.sep4androidapp.connection;

import android.os.AsyncTask;

import com.google.android.gms.common.api.Api.*;
import com.google.gson.JsonElement;

public class ClientAPI{


    /*
    -------COPY TO MANIFEST FILE (not needed on API>22)-------
    <uses-permission android:name="android.permission.INTERNET" />

     */


    private static ClientAPI INSTANCE = new ClientAPI();

    private final String URI = "https://zzleep-api.herokuapp.com/";
    private Client client;

    private ClientAPI()
    {

    }


    private void init()
    {

    }

    public static ClientAPI getInstance()
    {
        if(INSTANCE == null) {
            synchronized (ClientAPI.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientAPI();
                }
            }
        }
        return INSTANCE;
    }

    public void request(String address, JsonElement element)
    {


    }



}
