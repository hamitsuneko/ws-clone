package com.example.ws_session1.ui.main.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ws_session1.R;
import com.example.ws_session1.models.FeelingsResponse;
import com.example.ws_session1.models.LoginResponse;
import com.example.ws_session1.services.adapter.RecyclerCustomAdapter;
import com.example.ws_session1.services.network.NetworkServices;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {

    private View root;
    private LoginResponse loginResponse;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_menu, container, false);

        if (getArguments() != null) {
            loginResponse = (LoginResponse) getArguments().getSerializable("tokenModel");
        }

        findViews();
        return root;
    }

    private void findViews() {
        recyclerView = root.findViewById(R.id.feelings_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ((TextView) root.findViewById(R.id.menu_user_name)).setText(String.format("С возвращением, %s", loginResponse.getNickName()));
        Glide.with(requireContext()).load(loginResponse.getAvatar()).into((ImageView) root.findViewById(R.id.menu_user_image));
        getFeelings();
    }

    private void getFeelings() {
        NetworkServices.getInstance().getJSONApi().feelings().enqueue(new Callback<FeelingsResponse>() {
            @Override
            public void onResponse(Call<FeelingsResponse> call, Response<FeelingsResponse> response) {
                if (response.code() / 100 == 2)
                    recyclerView.setAdapter(new RecyclerCustomAdapter(getContext(), response.body().getData()));
                else Toast.makeText(getContext(), response.code() + " ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<FeelingsResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
