package com.example.ws_session1.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.ws_session1.R;
import com.example.ws_session1.models.LoginModel;
import com.example.ws_session1.models.LoginResponse;
import com.example.ws_session1.services.network.NetworkServices;
import com.example.ws_session1.ui.boarding.OnBoarding;
import com.example.ws_session1.ui.login.LoginActivity;
import com.example.ws_session1.ui.main.MainScreenActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginModel loginModel = loadPreferences();
                if(!loginModel.getEmail().equals("untitled")){login(loginModel);}
                else startActivity(new Intent(getApplicationContext(), OnBoarding.class));
            }
        }, 2000);
    }

    private void login(LoginModel loginModel) {
        NetworkServices.getInstance().getJSONApi().login(loginModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() / 100 == 2) {
                    Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                    intent.putExtra("userInfo", response.body());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SplashScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private LoginModel loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return new LoginModel(sharedPreferences.getString("login", "untitled"), sharedPreferences.getString("pass", "1234"));
    }
}