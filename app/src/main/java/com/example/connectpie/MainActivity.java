package com.example.connectpie;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.example.connectpie.RetrofitIntrigation.RetrofitClient;
import com.example.connectpie.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String Api_serverFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("LogDetails",MODE_PRIVATE);
        String log_flage = pref.getString("flag",null);
//        Api_serverFlag = null;
        CheckServerOffOrOn();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                if(Api_serverFlag == null){
                    alert();
                }else{
                     if(log_flage == null){
                                Intent in = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(in);
                     }else{
                                Intent in = new Intent(MainActivity.this,home_user.class);
                                startActivity(in);
                           }

                    }
            }

        },10000);

    }
    public void alert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Server is off");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.show();

    }
    public  void CheckServerOffOrOn(){
        Call<Void>  call = RetrofitClient
                .getInstance()
                .getApi()
                .Servertest();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                     Api_serverFlag = "Accessed";
                }else{
                    Api_serverFlag = null;
                    Log.d(TAG, "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: error due to server"+t.getMessage());
                    Api_serverFlag = null;
            }
        });
    }
}