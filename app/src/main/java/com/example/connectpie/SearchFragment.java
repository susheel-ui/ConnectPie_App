package com.example.connectpie;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.connectpie.Response.loginResponse;
import com.example.connectpie.Response.sendRequestmodule;
import com.example.connectpie.RetrofitIntrigation.RetrofitClient;
import com.example.connectpie.databinding.FragmentSearchBinding;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // own viewbinding of fragment
    FragmentSearchBinding binding ;
    List<loginResponse> searchlist;
    ArrayAdapter adapter;
    String useridl;


    public SearchFragment() {
        // Required empty public constructor
    }
public SearchFragment(String userid){
        if(userid==null){
            SharedPreferences pref = getContext().getSharedPreferences("LogDetails",MODE_PRIVATE);
            useridl = pref.getString("userid",null);

        }else{
            this.useridl = userid;
        }



}
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        searchlist = new ArrayList<>();
//        searchlist.add("item 1");
//        searchlist.add("item 2");
//        searchlist.add("item 3");

        getalluser();

        adapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,searchlist);
        binding.listviewSearchResult.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                Log.d(TAG, "run: "+"dataset changed");
            }
        },5000);




//        binding.searchBox.setEndIconOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String enteredText = binding.searchEdittext.getText().toString();
//                if(!enteredText.isEmpty()){
////                    searchuser(enteredText);
//                }else {
//                    Toast.makeText(getContext(), "pls enter userid", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                  searchuser(query);
//                Toast.makeText(getContext(), "your result"+result_user.getName(), Toast.LENGTH_SHORT).show();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchlist.clear();
               adapter.notifyDataSetChanged();
                return true;
            }
        });

            binding.listviewSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    loginResponse userinfo = searchlist.get(position);
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("Profile ");
                    alert.setMessage("Name :-"+userinfo.getName()+" user id :- "+ userinfo.getUserid());
                    alert.setPositiveButton("Add Friends", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendFriendRequest(useridl,userinfo.getUserid());
                        }
                    });
                    alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alert.show();


                }
            });







//        binding.searchview.onKeyDown(KeyEvent.KEYCODE_ENTER,)

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    loginResponse response_result =null;
    public void searchuser(String userid){


        Call<loginResponse> call = RetrofitClient.getInstance()
                .getApi()
                .getuser(userid);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if (response.code() == 200){
                    searchlist.clear();
                   response_result = response.body();
                   if(!response_result.getUserid().equals(useridl)){
                       searchlist.add(response_result);
                   }

                   Toast.makeText(getContext(), ""+searchlist, Toast.LENGTH_SHORT).show();
                   binding.listviewSearchResult.deferNotifyDataSetChanged();


                } else if (response.body() == null) {
                    Toast.makeText(getContext(), "there is not any "+userid, Toast.LENGTH_SHORT).show();

                } else{
                    Toast.makeText(getContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Server is down", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void getalluser(){
        Call<List<loginResponse>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getallusers();
        call.enqueue(new Callback<List<loginResponse>>() {
            @Override
            public void onResponse(Call<List<loginResponse>> call, Response<List<loginResponse>> response) {
                if(response.code() == 200){
                   for (loginResponse val :response.body()){
                            if(!val.getUserid().equals(useridl)){
                                searchlist.add(val);
                            }
                   }
                    Log.d(TAG, "onResponse: code = "+response.code());
                     adapter.notifyDataSetChanged();
                    binding.listviewSearchResult.deferNotifyDataSetChanged();

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

    // send Friend request
    public void sendFriendRequest(String usridSender,String usridReciever){
        if(usridSender!= null && usridReciever != null){
            sendRequestmodule obj = new sendRequestmodule(usridSender,usridReciever,"pending");

                Call<Void> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .SendFriendRequest(obj);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() ==200){
                            Toast.makeText(getContext(), "Successfully send", Toast.LENGTH_SHORT).show();
                        }else if(response.code() == 208){
                            Toast.makeText(getContext(), "You Are already Friend", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "internal Error"+response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "Server is not workig......"+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



        }
        else{
            Toast.makeText(getContext(), "null entity", Toast.LENGTH_SHORT).show();
        }


    }
}