package com.example.ws_session1.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ws_session1.R;
import com.example.ws_session1.models.LoginModel;
import com.example.ws_session1.models.LoginResponse;
import com.example.ws_session1.services.network.NetworkServices;
import com.example.ws_session1.ui.main.MainScreenActivity;
import com.example.ws_session1.ui.registration.RegistrationActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setOnClick();
    }

    private void setOnClick() {
        findViewById(R.id.login_signButton).setOnClickListener(this);
        findViewById(R.id.login_profileButton).setOnClickListener(this);
        email = findViewById(R.id.login_emailInput);
        pass = findViewById(R.id.login_passInput);
        if(getLogin().length() > 1){ email.setText(getLogin());}
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getLogin().length() > 1){ email.setText(getLogin());}
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(getLogin().length() > 1){ email.setText(getLogin());}
    }


    private void signIn() {
        String mail = email.getText().toString();
        String password = pass.getText().toString();

        if (mail.contains("@") && mail.length() > 8 && password.length() > 0) {
            signRequest(new LoginModel(mail, password));
        } else
            Toast.makeText(getApplicationContext(), "Проверьте правильность введённых данных", Toast.LENGTH_SHORT).show();
    }

    private void signRequest(LoginModel loginModel) {
        NetworkServices.getInstance().getJSONApi().login(loginModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() / 100 == 2) {
                    Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                    intent.putExtra("userInfo", response.body());
                    saveLoginInfo(loginModel.getEmail(), loginModel.getPassword());
                    startActivity(intent);
                } else
                    Toast.makeText(LoginActivity.this, "Проверьте логин и пароль", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_signButton: {
                signIn();
            }
            break;
            case R.id.login_profileButton: {

            }
            break;
        }
    }

    private void saveLoginInfo(String mail, String pass) {
        SharedPreferences prefs = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("login", mail);
        prefEdit.putString("pass", pass);
        prefEdit.apply();
    }

    private String getLogin(){
        return getSharedPreferences("loginInfo",MODE_PRIVATE).getString("login", "");
    }
}