package com.example.ws_session1.ui.boarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ws_session1.R;
import com.example.ws_session1.ui.login.LoginActivity;
import com.example.ws_session1.ui.registration.RegistrationActivity;

public class OnBoarding extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        setOnClick();
    }

    private void setOnClick() {
        findViewById(R.id.onBoarding_LoginButton).setOnClickListener(this);
        findViewById(R.id.onBoarding_RegisterText).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.onBoarding_LoginButton:{
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }break;
            case R.id.onBoarding_RegisterText:{
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }break;
        }
    }
}