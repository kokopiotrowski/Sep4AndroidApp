package com.example.sep4androidapp.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("https://zzleep-api-dev.herokuapp.com/api/").addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();

    private static UserApi userApi = retrofit.create(UserApi.class);
    private static ReportApi reportApi = retrofit.create(ReportApi.class);
    private static RoomConditionApi roomConditionApi = retrofit.create(RoomConditionApi.class);
    private static AccountDevicesApi accountDevicesApi = retrofit.create(AccountDevicesApi.class);
    private static FactApi factApi = retrofit.create(FactApi.class);
    private static PreferenceApi preferenceApi = retrofit.create(PreferenceApi.class);


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
