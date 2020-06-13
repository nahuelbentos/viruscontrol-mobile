package com.grupo14.viruscontrol.viruscontroluy.services;

import com.grupo14.viruscontrol.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontrol.viruscontroluy.modelos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface VirusControlService {

    @POST("autenticacion/entrar/ciudadano")
    Call<LoginResponse> backendLogin(@Body LoginRequest body);

    @GET("ciudadano/visita/sintomas")
    Call<List<Sintoma>> getSintomas(@Header("Authorization") String sessionToken);

}
