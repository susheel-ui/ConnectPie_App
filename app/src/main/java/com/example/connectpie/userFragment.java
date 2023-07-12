package com.example.connectpie;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.connectpie.Response.loginResponse;
import com.example.connectpie.RetrofitIntrigation.RetrofitClient;
import com.example.connectpie.databinding.FragmentUserBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class userFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentUserBinding binding;
    loginResponse user_info  = null;
    String userid =null ;
    boolean flag = false;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userFragment() {
        // Required empty public constructor
    }
    public userFragment(loginResponse usrid){
        this.user_info = usrid;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userFragment newInstance(String param1, String param2) {
        userFragment fragment = new userFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentUserBinding.inflate(getLayoutInflater());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        binding.btnlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences pref = getContext().getSharedPreferences("LogDetails",MODE_PRIVATE);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("flag",null);
//                editor.apply();
//                getuserDataApiCall(userid);
//                user_info.setStatus("inactive");
//                updatestatus(user_info);
//
////                getActivity().finish();
//
//            }
//        });
        if(user_info!= null){
            binding.usersName.setText(user_info.getName());
            binding.tvusername.setText(user_info.getUserid());
            binding.tvemail.setText(user_info.getEmail());
        }
        binding.btnchats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent in = new Intent(getContext(),chatting_Activity.class);
            in.putExtra("user_info",user_info);
            if(user_info!= null)
                startActivity(in);
             }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return binding.getRoot();
    }




}