package com.example.sep4androidapp.connection;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.sep4androidapp.connection.apis.AccountDevicesApi;
import com.example.sep4androidapp.connection.apis.FactApi;
import com.example.sep4androidapp.connection.apis.PreferenceApi;
import com.example.sep4androidapp.connection.apis.ReportApi;
import com.example.sep4androidapp.connection.apis.RoomConditionApi;
import com.example.sep4androidapp.connection.apis.SleepTrackingApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ServiceGenerator {
    private static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String str = json.getAsJsonPrimitive().getAsString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            return dateTime;
        }
    }).create();
    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor()).build();
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
            baseUrl("https://zzleep-api.herokuapp.com/api/").addConverterFactory(GsonConverterFactory.create(gson)).client(client);
    private static Retrofit retrofit = retrofitBuilder.build();

    private static ReportApi reportApi = retrofit.create(ReportApi.class);
    private static RoomConditionApi roomConditionApi = retrofit.create(RoomConditionApi.class);
    private static AccountDevicesApi accountDevicesApi = retrofit.create(AccountDevicesApi.class);
    private static FactApi factApi = retrofit.create(FactApi.class);
    private static PreferenceApi preferenceApi = retrofit.create(PreferenceApi.class);
    private static SleepTrackingApi sleepTrackingApi = retrofit.create(SleepTrackingApi.class);

    public static ReportApi getReportApi() {
        return reportApi;
    }

    public static RoomConditionApi getRoomConditionApi() {
        return roomConditionApi;
    }

    public static AccountDevicesApi getAccountDevicesApi() {
        return accountDevicesApi;
    }

    public static FactApi getFactApi() {
        return factApi;
    }

    public static PreferenceApi getPreferenceApi() {
        return preferenceApi;
    }

    public static SleepTrackingApi getSleepTrackingApi() {
        return sleepTrackingApi;
    }
}
