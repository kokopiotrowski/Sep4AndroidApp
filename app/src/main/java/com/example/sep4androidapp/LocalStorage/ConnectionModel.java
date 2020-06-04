package com.example.sep4androidapp.LocalStorage;

public class ConnectionModel {
    private int type;
    private boolean isConnected;

    public ConnectionModel(int type, boolean isConnected) {
        this.type = type;
        this.isConnected = isConnected;
    }

    public boolean getIsConnected() {
        return isConnected;
    }
}
