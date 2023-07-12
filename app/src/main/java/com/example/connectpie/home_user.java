package com.example.connectpie;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.connectpie.Response.loginResponse;
import com.example.connectpie.RetrofitIntrigation.RetrofitClient;
import com.example.connectpie.databinding.ActivityHomeUserBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home_user extends AppCompatActivity {
    ActivityHomeUserBinding binding;
    loginResponse  user_info = new loginResponse();
    boolean flag=false;
    HomeFragment homeFragment = new HomeFragment();
    userFragment  userFragment;
    SearchFragment searchFragment;
    friendFragment friendFragment;
    String usrid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.text1.setVisibility(View.VISIBLE);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("LogDetails",MODE_PRIVATE);
        usrid = pref.getString("userid",null);
        if(usrid != null){
            getuserDataApiCall(usrid);

        }





    }

    @Override
    protected void onStart() {
        super.onStart();



        loadFragment(homeFragment,true);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                user_info.setStatus("active");
                updatestatus(user_info);
//                    binding.toolbar.setTitle(""+user_info.getName());

            }
        },5000);

//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Log.d(TAG, "run: "+"second");
//            }
//        },0,1000);

        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.btnlogout:
                        user_info.setStatus("inactive");
                        updatestatus(user_info);
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("LogDetails",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("flag",null);
                        editor.apply();
                        finish();
                        break;
                }
                return true;
            }
        });


        binding.bottomNavbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case  R.id.btmNavUser:
                        loadFragment(userFragment,false);
                        break;
                    case R.id.btmNavHome:
                        loadFragment(homeFragment,false);
                        break;
                    case R.id.btmNavAddFriend:
                        loadFragment(friendFragment,false);
                        break;
                    case R.id.btmNavSearch:
                        loadFragment(searchFragment,false);
                        break;
                }
                return true;
            }
        });


        Log.d(TAG, "onCreate: "+ user_info.getUserid());


    }

    public void getuserDataApiCall(String usrid){

        Call<loginResponse> call = RetrofitClient.getInstance()
                .getApi()
                .getuser(usrid);
        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if (response.code() == 200){
//                    user.setId(response.body().getId());
//                    user.setName(response.body().getName());
//                    user.setPassword(response.body().getPassword());
//                    user.setStatus(response.body().getStatus());
//                    user.setEmail(response.body().getEmail());
                    user_info = response.body();
                    flag= true;
                    AssignFragments();
                    if(user_info!= null) {
                        binding.progressBar.setVisibility(View.GONE);
                        binding.text1.setVisibility(View.GONE);
                    }

                }
                else{
                    Log.d(TAG, "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });


    }

    public void updatestatus(loginResponse usr){
        Call<loginResponse> call = RetrofitClient.getInstance()
                .getApi()
                .updateusr(user_info);
        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                    if (response.code()==200){
                        user_info = response.body();
                        Log.d(TAG, "onResponse: "+"successfull updated");


                    }else if(response.code() == 203){
                        Log.d(TAG, "onResponse: "+response.code());
                    }
                    else {
                        Log.d(TAG, "onResponse: "+response.code());
                    }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    public void loadFragment(Fragment ft, boolean flag){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.fragment_container,new HomeFragment());
        if(flag){
            transaction.add(binding.framehome.getId(),ft);
        }else{
            transaction.replace(binding.framehome.getId(),ft);
        }

        transaction.commit();
    }

    public void AssignFragments(){
        searchFragment = new SearchFragment(usrid);
        userFragment = new userFragment(user_info);
        friendFragment = new friendFragment(usrid);
    }

}