package com.example.ws_session1.services.network;

import com.example.ws_session1.models.FeelingsResponse;
import com.example.ws_session1.models.LoginModel;
import com.example.ws_session1.models.LoginResponse;
import com.example.ws_session1.models.QuotesResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
    @POST("user/login")
    Call<LoginResponse> login(@Body LoginModel loginModel);

    @GET("feelings")
    Call<FeelingsResponse> feelings();

    @GET("quotes")
    Call<QuotesResponse> quotes();
}
