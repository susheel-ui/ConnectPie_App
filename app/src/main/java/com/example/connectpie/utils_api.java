package com.example.connectpie;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.connectpie.Response.loginResponse;
import com.example.connectpie.RetrofitIntrigation.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class utils_api {
    loginResponse Return_response;

    public loginResponse getuserinfo(String userid){


        Call<loginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getuser(userid);
        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if (response.code() == 200){
                    Return_response = response.body();
                    Log.d(TAG, "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
        if(Return_response!= null){
            return Return_response;

        }

        return null;

    }
}
