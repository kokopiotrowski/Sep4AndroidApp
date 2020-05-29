//package com.example.sep4androidapp.Status;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.Network;
//import android.net.NetworkInfo;
//import android.util.Log;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//
//public class AppStatus {
//
//    private static AppStatus instance = new AppStatus();
//    static Context context;
//    ConnectivityManager connectivityManager;
//    ConnectivityManager.NetworkCallback wifiInfo, mobileInfo;
//    boolean connected = false;
//
//    public static AppStatus getInstance(Context ctx) {
//        context = ctx.getApplicationContext();
//        return instance;
//    }
//    public static boolean isConnected(Context context) {
//        ConnectivityManager cm = (ConnectivityManager)context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        ConnectivityManager.NetworkCallback activeNetwork = cm.getActiveNetwork();
//        if (activeNetwork != null && activeNetwork.isConnected()) {
//            try {
//                URL url = new URL("http://www.google.com/");
//                HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
//                urlc.setRequestProperty("User-Agent", "test");
//                urlc.setRequestProperty("Connection", "close");
//                urlc.setConnectTimeout(1000); // mTimeout is in seconds
//                urlc.connect();
//                if (urlc.getResponseCode() == 200) {
//                    return true;
//                } else {
//                    return false;
//                }
//            } catch (IOException e) {
//                Log.i("warning", "Error checking internet connection", e);
//                return false;
//            }
//        }
//
//        return false;
//
//    }
//}