package com.example.connectpie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import com.example.connectpie.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("LogDetails",MODE_PRIVATE);
                String log_flage = pref.getString("flag",null);
                if(log_flage == null){
                    Intent in = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(in);
                }
                else{
                    Intent in = new Intent(MainActivity.this,home_user.class);
                    startActivity(in);
                }

            }
        },5000);

    }
}