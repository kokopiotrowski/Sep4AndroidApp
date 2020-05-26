package com.example.sep4androidapp.connection;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ServiceGenerator {
    //    private static Gson gson = new GsonBuilder().
//            setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//            Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
//            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//            return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime();
//            return LocalDateTime.now();
            String str = json.getAsJsonPrimitive().getAsString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            return dateTime;
        }
    }).create();
    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor()).build();
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
            baseUrl("https://zzleep-api-dev.herokuapp.com/api/").addConverterFactory(GsonConverterFactory.create(gson)).client(client);
    private static Retrofit retrofit = retrofitBuilder.build();

    private static UserApi userApi = retrofit.create(UserApi.class);
    private static ReportApi reportApi = retrofit.create(ReportApi.class);
    private static RoomConditionApi roomConditionApi = retrofit.create(RoomConditionApi.class);
    private static AccountDevicesApi accountDevicesApi = retrofit.create(AccountDevicesApi.class);
    private static FactApi factApi = retrofit.create(FactApi.class);
    private static PreferenceApi preferenceApi = retrofit.create(PreferenceApi.class);
    private static SleepTrackingApi sleepTrackingApi = retrofit.create(SleepTrackingApi.class);

    public static UserApi getUserApi() {
        return userApi;
    }

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

    /*
    COPY THIS METHOD TO THE REGISTER THAT USES UserApi

        public void requestUser(int id)
        {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call call = userApi.getUser(id);
        call.enqueue(new Callback(){
            @Override
            public void onResponse(Call call, Response response)
            {
            if(response.code() == 200)
            {

            }
            }

        });
        }


     */

}
