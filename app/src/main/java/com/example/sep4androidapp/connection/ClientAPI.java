package com.example.sep4androidapp.connection;

import com.google.android.gms.common.api.Api;
import com.google.gson.JsonElement;

public class ClientAPI {

    private static ClientAPI INSTANCE = new ClientAPI();

    private final String MAIN = "";


    private ClientAPI()
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
