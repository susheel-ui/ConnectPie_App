package com.example.connectpie;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.example.connectpie.Response.loginResponse;
import com.example.connectpie.RetrofitIntrigation.RetrofitClient;
import com.example.connectpie.databinding.ActivityNewAccountBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAccountActivity extends AppCompatActivity {
    ActivityNewAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewAccountBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        // code for taking data from form

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.EtSignUpName.getText().toString();
                String userid = binding.EtSignupUserId.getText().toString();
                String email = binding.EtSignUpEmail.getText().toString();
                String firstpass = binding.EtSignupPassword.getText().toString();
                String confirmpass = binding.EtSignupConfirmPassword.getText().toString();

                if(firstpass.equals(confirmpass) && !firstpass.isEmpty() && !confirmpass.isEmpty()){
                    // code if matched
                    if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        if(!name.isEmpty()){
                            if(!userid.isEmpty()){
                                JSONObject obj = new JSONObject();
                                loginResponse user = new loginResponse(name,userid,firstpass,"Inactive",email);
                               postdata(user);


                            }else{
                                binding.TILSignUPpUserId.setEndIconDrawable(R.drawable.baseline_star_rate_24);
                            }

                        }else{
                            binding.TILname.setEndIconDrawable(R.drawable.baseline_star_rate_24);
                        }

                    }else{
                        binding.TILsignUpEmail.setEndIconDrawable(R.drawable.baseline_star_rate_24);
                    }

                }
                else{
                    // code if not matched
                   AlertDialog.Builder alert = new AlertDialog.Builder(NewAccountActivity.this);
                   alert.setTitle("Error");
                   alert.setMessage("Password does'nt matched");
                   alert.setPositiveButton("ok ", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           binding.EtSignupPassword.setText("");
                           binding.EtSignupConfirmPassword.setText("");
                       }
                   });
                   alert.show();



                }





            }
        });



    }

    public void postdata(loginResponse obj){
        Call<loginResponse> call = RetrofitClient.getInstance()
                .getApi()
                .createuser(obj);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if(response.code() == 200){
                    Log.d(TAG, "onResponse: "+response.body());
                    AlertDialog.Builder alert = new AlertDialog.Builder(NewAccountActivity.this);
                    alert.setTitle("Successfull");
                    alert.setMessage("userid:"+response.body().getUserid()+"\n password: "+response.body().getPassword());
                    alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alert.show();

                }else {
                    Log.d("response error",""+response.code());
// alert show to exist member same email or userid
                    AlertDialog.Builder alert = new AlertDialog.Builder(NewAccountActivity.this);
                    alert.setMessage("email or userid is exist aleready");
                    alert.setPositiveButton("ok ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Log.d("onFailure error",""+t.getMessage());


            }
        });
    }


}