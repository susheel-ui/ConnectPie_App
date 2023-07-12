package com.example.connectpie;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.connectpie.Response.InboxFriendRequestmodule;
import com.example.connectpie.Response.loginResponse;
import com.example.connectpie.RetrofitIntrigation.RetrofitClient;
import com.example.connectpie.databinding.ActivityChattingBinding;
import com.example.connectpie.databinding.ChattingFriendlistBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chatting_Activity extends AppCompatActivity {
    ActivityChattingBinding binding;
    List<loginResponse> friendlist;
    List<loginResponse> listFriends  = new ArrayList<>();
    loginResponse userInfo;
RecycleAdapter adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChattingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent get = getIntent();
        userInfo =(loginResponse) get.getSerializableExtra("user_info");
        binding.listview.setLayoutManager(new LinearLayoutManager(chatting_Activity.this));
        Log.d(TAG, "onCreate: "+userInfo);
        friendlist = new ArrayList<>();

        GetFriendlist(userInfo.getUserid());

//        binding.listview.setLayoutManager(new LinearLayoutManager(chatting_Activity.this));

    }

    @Override
    protected void onStart() {
        super.onStart();





    }

    public void GetFriendlist(String userid){

       Call<List<loginResponse>> call = RetrofitClient
               .getInstance()
               .getApi()
               .getAllFriend(userid);

       call.enqueue(new Callback<List<loginResponse>>() {
           @Override
           public void onResponse(Call<List<loginResponse>> call, Response<List<loginResponse>> response) {
               if(response.code() == 200){
                   friendlist = response.body();
                   Log.d(TAG, "onResponse: call api"+response.body());
                   adapter = new RecycleAdapter(friendlist);
                   binding.listview.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
               }
               else{
                   Log.d(TAG, "onResponse: "+response.code());
               }
           }

           @Override
           public void onFailure(Call<List<loginResponse>> call, Throwable t) {
               Log.d(TAG, "onFailure: "+t.getMessage());
           }
       });


    }

    class viewholder  extends RecyclerView.ViewHolder{

        TextView tv1,tv2;


        @SuppressLint("MissingInflatedId")
        public viewholder(@NonNull View itemView) {
            super(itemView);

//            View view = getLayoutInflater().inflate(R.layout.chatting_friendlist,null);
//            tv1= view.findViewById(R.id.CFL_name);
//            tv2 = view.findViewById(R.id.CFL_userid);
            tv1 = itemView.findViewById(R.id.CFL_name);
            tv2 = itemView.findViewById(R.id.CFL_userid);


        }

    }
    class RecycleAdapter extends RecyclerView.Adapter<viewholder>{

        List<loginResponse> list;
        RecycleAdapter(List<loginResponse> list){
                this.list = list;
        }

        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.chatting_friendlist,null);
            viewholder  vh = new viewholder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder holder, int position) {
            loginResponse moduledata = list.get(position);
            holder.tv1.setText(""+moduledata.getName());
            holder.tv2.setText(""+moduledata.getUserid());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(chatting_Activity.this,Activity_chating_page.class);
                    in.putExtra("user_info",moduledata);
                    startActivity(in);
                }
            });

        }

        @Override
        public int getItemCount() {
            return friendlist.size();
        }
    }





}