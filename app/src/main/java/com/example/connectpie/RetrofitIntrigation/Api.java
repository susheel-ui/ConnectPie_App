package com.example.connectpie.RetrofitIntrigation;

import com.example.connectpie.Response.InboxFriendRequestmodule;
import com.example.connectpie.Response.loginResponse;
import com.example.connectpie.Response.login_info;
import com.example.connectpie.Response.sendRequestmodule;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @GET("home/")
    Call<Void> Servertest();
    @POST("users/login")
    Call<Void> login(@Body login_info info);

    @POST("users/createuser")
    Call<loginResponse> createuser(@Body loginResponse obj);

    @GET("users/{userid}")
    Call<loginResponse> getuser(@Path("userid") String userid);

    @PUT("users/updatestatus")
    Call<loginResponse> updateusr(@Body loginResponse user);

    @POST("users/sendFriRequest")
    Call<Void> SendFriendRequest(@Body sendRequestmodule obj);

    @GET("users")
    Call<List<loginResponse>> getallusers();

    @GET("users/inboxRequests/{userid}")
    Call<List<InboxFriendRequestmodule>> getFriendRequests(@Path("userid") String userid);

    @PUT("users/inboxRequest")
    Call<Void> ResponseforRequest(@Body InboxFriendRequestmodule body);

    @GET("users/getFriendlist/{userid}")
    Call<List<loginResponse>> getAllFriend(@Path("userid") String userid);








}
