package com.grupo14.viruscontroluy.retrofit;

import com.grupo14.viruscontroluy.modelos.LoginRequest;
import com.grupo14.viruscontroluy.modelos.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ILoginBackend {


    @POST("autenticacion/entrar/ciudadano")
    Call<LoginResponse> loginBackend(@Body LoginRequest body);

    @DELETE("autenticacion/salir/")
    Call<String> logoutBackend(@Header("Authorization") String accessToken);

}
