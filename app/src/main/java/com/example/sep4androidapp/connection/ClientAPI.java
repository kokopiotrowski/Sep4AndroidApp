package com.example.sep4androidapp.connection;

import android.os.AsyncTask;

import com.example.sep4androidapp.Entities.User;
import com.google.android.gms.common.api.Api.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class ClientAPI{  // BTW, THIS CLASS IS POINTLESS, JUST IGNORE IT, I DID SOMETHING THAT I THOUGHT WILL BE USED BUT IT WILL NOT. FUCKING PIECE OF CRAP


    /*
    -------COPY TO MANIFEST FILE (not needed on API>22)-------
    <uses-permission android:name="android.permission.INTERNET" />

     */


    private static ClientAPI INSTANCE = new ClientAPI();

    private final String URI = "https://zzleep-api.herokuapp.com/";
    private String extraAddress = "";
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




}
