package com.example.connectpie;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connectpie.Response.*;

import com.example.connectpie.RetrofitIntrigation.RetrofitClient;
import com.example.connectpie.databinding.ActivityLoginBinding;
import com.example.connectpie.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userid = binding.EtLogInUserId.getText().toString();
                String password = binding.EtLogInPass.getText().toString();


                if(userid.length()>0&& password.length()>0){
                    login_info info = new login_info(userid,password);
                    ApiCallLogin(info);
                }else{
                    if(userid.length()<=0 ){
                        Log.d(TAG, "onClick: "+"fill user id");
                        binding.TILuserid.setEndIconDrawable(R.drawable.baseline_star_rate_24);
                    }
                    if(password.length()<=0){
                        binding.TILPassword.setEndIconDrawable(R.drawable.baseline_star_rate_24);
                    }

                }
            }
        });

        binding.TvbtnnotAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this,NewAccountActivity.class);
                startActivity(in);
            }
        });


    }

    public void ApiCallLogin(login_info info){
        Call<Void> call = RetrofitClient.getInstance()
                .getApi()
                .login(info);
       call.enqueue(new Callback<Void>() {
           @Override
           public void onResponse(Call<Void> call, Response<Void> response) {
               if(response.code() == 200){
                   Log.d("log","login successfull with code"+response.code());
                   SharedPreferences.Editor SPeditor = getApplicationContext().getSharedPreferences("LogDetails",MODE_PRIVATE).edit();
                   SPeditor.putString("userid",info.getUserid());
//                   SPeditor.putString("password",info.getPassword());
                   SPeditor.putString("flag","login");
                   SPeditor.apply();
                   Intent in = new Intent(LoginActivity.this,home_user.class);
                   startActivity(in);
               }
               else if(response.code() == 203){
                   Log.d("log","login unsuccessfull with code"+response.code());
                   binding.errorShowTV.setText("Incorrect id or password");
               }else{
                   Log.d("log","login  errorsuccessfull with code"+response.code());
                   binding.errorShowTV.setText("Server Error");
               }
           }

           @Override
           public void onFailure(Call<Void> call, Throwable t) {
               Log.d("log","login successfull with code"+t.getMessage()+"\n "+t.getCause());

           }
       });

    }
}