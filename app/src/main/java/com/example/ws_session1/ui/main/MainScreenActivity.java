package com.example.ws_session1.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ws_session1.R;
import com.example.ws_session1.models.LoginResponse;
import com.example.ws_session1.ui.main.listening.ListeningFragment;
import com.example.ws_session1.ui.main.menu.MenuFragment;
import com.example.ws_session1.ui.main.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreenActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        BottomNavigationView bottomNavigationView = findViewById(R.id.mainScreenActivity_NavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menu_main);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main: {
                Fragment fragment = new MenuFragment();
                fragment.setArguments(passData());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainScreenActivity_Container, fragment).commit();
                return true;
            }
            case R.id.menu_profile: {
                Fragment fragment = new ProfileFragment();
                fragment.setArguments(passData());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainScreenActivity_Container, fragment).commit();
                return true;
            }
            case R.id.menu_listening: {
                Fragment fragment = new ListeningFragment();
                fragment.setArguments(passData());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainScreenActivity_Container, fragment).commit();
                return true;
            }
        }
        return false;
    }

    private Bundle passData() {
        Bundle bundle = new Bundle();
        LoginResponse loginResponse = (LoginResponse) getIntent().getSerializableExtra("userInfo");
        bundle.putSerializable("tokenModel", loginResponse);
        return bundle;
    }
}