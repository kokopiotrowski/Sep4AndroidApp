package com.example.sep4androidapp.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("https://zzleep-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();

    private static UserApi userApi = retrofit.create(UserApi.class);


    public static UserApi getUserApi()
    {
        return userApi;
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
