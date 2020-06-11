package com.grupo14.viruscontrol.viruscontroluy.services;

import com.grupo14.viruscontrol.viruscontroluy.modelos.Usuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VirusControlService {

    @FormUrlEncoded
    @POST("/autenticacion/entrar/ciudadano")
    Call<LoginResponse> backendLogin(
            @Field("nombre") String nombre,
            @Field("appelido") String apellido,
            @Field("correo") String correo,
            @Field("username") String username
    );


}
