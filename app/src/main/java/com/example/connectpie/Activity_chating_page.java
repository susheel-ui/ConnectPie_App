package com.example.connectpie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.connectpie.Response.loginResponse;
import com.example.connectpie.databinding.ActivityChatingPageBinding;

public class Activity_chating_page extends AppCompatActivity {
    ActivityChatingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent getin = getIntent();
        loginResponse userinfo =(loginResponse) getin.getSerializableExtra("user_info");
        if(userinfo != null){
            binding.toolbar.setTitle(userinfo.getName());
        }
    }
}