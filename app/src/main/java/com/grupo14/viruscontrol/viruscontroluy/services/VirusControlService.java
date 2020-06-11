package com.grupo14.viruscontrol.viruscontroluy.services;

import com.grupo14.viruscontrol.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontrol.viruscontroluy.modelos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VirusControlService {

    @FormUrlEncoded
    @POST("autenticacion/entrar/ciudadano")
    Call<LoginResponse> backendLogin(
            @Field("nombre") String nombre,
            @Field("apellido") String apellido,
            @Field("correo") String correo,
            @Field("username") String username
    );

    @GET("ciudadano/visita/sintomas")
    Call<List<Sintoma>> getSintomas();


}
