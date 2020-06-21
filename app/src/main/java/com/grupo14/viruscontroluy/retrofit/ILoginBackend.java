package com.grupo14.viruscontroluy.retrofit;

import com.grupo14.viruscontroluy.modelos.LoginRequest;
import com.grupo14.viruscontroluy.modelos.LoginResponse;
import com.grupo14.viruscontroluy.modelos.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ILoginBackend {


    @POST("autenticacion/entrar/ciudadano")
    Call<LoginResponse> loginBackend(@Body LoginRequest body);

    @PUT("autenticacion/validar_datos")
    Call<Void> validarDatosBackend(@Body Usuario body);

    @DELETE("autenticacion/salir/")
    Call<Void> logoutBackend(@Header("authorization") String accessToken);

}
