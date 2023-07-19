package com.example.connectpie.RetrofitIntrigation;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String BASE_URL = "http://192.168.29.160:9090/connectpie/";
    public static RetrofitClient restrofitClient;
    public static Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    // get instance
    public static synchronized RetrofitClient getInstance(){
    if(restrofitClient == null){
        restrofitClient = new RetrofitClient();

    }
    return restrofitClient;

    }
    public Api getApi(){
        return retrofit.create(Api.class);

    }

}
