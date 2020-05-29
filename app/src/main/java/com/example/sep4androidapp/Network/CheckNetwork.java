package com.example.sep4androidapp.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.util.Log;

import androidx.annotation.NonNull;

public class CheckNetwork {
    private Context context;

    public CheckNetwork(Context context) {
        this.context = context;
    }


    public void registerDefaultNetworkCallback(){
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            assert connectivityManager != null;
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull android.net.Network network) {
                    super.onAvailable(network);
                    Variables.isNetworkConnected = true;
                    Log.d("FLABS:", "onAvailable");
                }

                @Override
                public void onLost(@NonNull android.net.Network network) {
                    super.onLost(network);
                    Variables.isNetworkConnected = false;
                    Log.d("FLABS:", "onLost");
                }

                @Override
                public void onBlockedStatusChanged(@NonNull Network network, boolean blocked) {
                    super.onBlockedStatusChanged(network, blocked);
                    Log.d("FLABS:", "onBlockedStatusChanged");

                }

                @Override
                public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    Log.d("FLABS:", "onCapabilitiesChanged");
                }

                @Override
                public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
                    super.onLinkPropertiesChanged(network, linkProperties);
                    Log.d("FLABS:", "onLinkPropertiesChanged");
                }

                @Override
                public void onLosing(@NonNull Network network, int maxMsToLive) {
                    super.onLosing(network, maxMsToLive);
                    Log.d("FLABS:", "onLosing");
                }

                @Override
                public void onUnavailable() {
                    super.onUnavailable();
                    Log.d("FLABS:", "onUnavailable");
                }
            });
        } catch (Exception e) {
            Log.d("FLABS: Exception in registerDefaultNetworkCallback", "hello");
            Variables.isNetworkConnected = false;
        }
    }



    public void registerNetworkCallback(){
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();

            assert connectivityManager != null;
            connectivityManager.registerNetworkCallback(builder.build(), new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull android.net.Network network) {
                    super.onAvailable(network);
                    Variables.isNetworkConnected = true;
                    Log.d("FLABS:", "onAvailable");
                }

                @Override
                public void onLost(@NonNull android.net.Network network) {
                    super.onLost(network);
                    Variables.isNetworkConnected = false;
                    Log.d("FLABS:", "onLost");
                }

                @Override
                public void onBlockedStatusChanged(@NonNull Network network, boolean blocked) {
                    super.onBlockedStatusChanged(network, blocked);
                    Log.d("FLABS:", "onBlockedStatusChanged");

                }

                @Override
                public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    Log.d("FLABS:", "onCapabilitiesChanged");
                }

                @Override
                public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
                    super.onLinkPropertiesChanged(network, linkProperties);
                    Log.d("FLABS:", "onLinkPropertiesChanged");
                }

                @Override
                public void onLosing(@NonNull Network network, int maxMsToLive) {
                    super.onLosing(network, maxMsToLive);
                    Log.d("FLABS:", "onLosing");
                }

                @Override
                public void onUnavailable() {
                    super.onUnavailable();
                    Log.d("FLABS:", "onUnavailable");
                }
            });

        } catch (Exception e) {
            Log.d("FLABS: Exception in registerNetworkCallback", "hello");
            Variables.isNetworkConnected = false;
        }
    }

}
