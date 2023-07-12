package com.example.connectpie;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.connectpie.Response.InboxFriendRequestmodule;
import com.example.connectpie.RetrofitIntrigation.RetrofitClient;
import com.example.connectpie.databinding.FragmentFriendBinding;
import com.example.connectpie.databinding.RequestListLayoutBinding;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link friendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class friendFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentFriendBinding binding;

    ArrayList<InboxFriendRequestmodule> RequestList;
    String profile_userid;

    ArrayAdapter<InboxFriendRequestmodule> adapter;
    CostomAdapter adapter1 ;



    public friendFragment() {
        // Required empty public constructor
    }
    public friendFragment(String userid){
        this.profile_userid = userid;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment friendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static friendFragment newInstance(String param1, String param2) {
        friendFragment fragment = new friendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentFriendBinding.inflate(getLayoutInflater());
        RequestList = new ArrayList<>();
//        adapter = new ArrayAdapter<InboxFriendRequestmodule>(getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,RequestList);
        adapter1 = new CostomAdapter(getContext());
        binding.listview.setAdapter(adapter1);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getRequestData(profile_userid);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    public void getRequestData(String userid){
        Call<List<InboxFriendRequestmodule>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getFriendRequests(userid);
        call.enqueue(new Callback<List<InboxFriendRequestmodule>>() {
            @Override
            public void onResponse(Call<List<InboxFriendRequestmodule>> call, Response<List<InboxFriendRequestmodule>> response) {
                if (response.code() == 200){
                    for (InboxFriendRequestmodule val :response.body()){
                        RequestList.add(val);
                        adapter1.notifyDataSetChanged();
                    }
                    if(RequestList.size()<=0){
                        Log.d(TAG, "onResponse: "+"Null list");
                    }else{
                        Log.d(TAG, "onResponse: "+RequestList);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<InboxFriendRequestmodule>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
    class CostomAdapter extends ArrayAdapter{

        Context context;
        RequestListLayoutBinding requestListLayoutBinding;


        public CostomAdapter(@NonNull Context context) {
            super(context,R.layout.request_list_layout,RequestList);
            this.context = context;



        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            InboxFriendRequestmodule user = RequestList.get(position);
            requestListLayoutBinding = RequestListLayoutBinding.inflate(getLayoutInflater());
            requestListLayoutBinding.username.setText(user.getFirstperson());
            requestListLayoutBinding.btnaccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionConfirm(user,"confirm");

                }
            });
            requestListLayoutBinding.btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionConfirm(user,"delete");
                }
            });
            return requestListLayoutBinding.getRoot() ;
        }
    }
    public void actionConfirm(InboxFriendRequestmodule inboxmodel,String status){
        inboxmodel.setStatus(status);

        Call<Void> call = RetrofitClient
                .getInstance()
                .getApi()
                .ResponseforRequest(inboxmodel);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code()==200){
                    Log.d(TAG, "onResponse: "+response.body());
                    Toast.makeText(getActivity().getApplicationContext(), "You are now friend of "+inboxmodel.getFirstperson(), Toast.LENGTH_LONG).show();
                }else if(response.code() == 202){
                    Toast.makeText(getActivity().getApplicationContext(), "deleted request", Toast.LENGTH_LONG).show();

                }
                else{
                    Log.d(TAG, "onResponse: "+response.code());
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}