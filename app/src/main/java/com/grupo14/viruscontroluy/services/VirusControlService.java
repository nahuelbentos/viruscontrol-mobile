package com.grupo14.viruscontroluy.services;

import com.grupo14.viruscontroluy.modelos.LoginRequest;
import com.grupo14.viruscontroluy.modelos.LoginResponse;
import com.grupo14.viruscontroluy.modelos.Ubicacion;
import com.grupo14.viruscontroluy.modelos.Sintoma;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface VirusControlService {

    @POST("autenticacion/entrar/ciudadano")
    Call<LoginResponse> backendLogin(@Body LoginRequest body);

    @GET("ciudadano/visita/sintomas")
    Call<List<Sintoma>> getSintomas(@Header("Authorization") String sessionToken);

    @POST("ciudadano/visita/confirmar")
    Call<ConfirmarVisitaResponse> postSolicitarVisita(@Header("Authorization") String sessionToken, @Body List<Sintoma> sintomaList);

    @PUT("/autenticacion/validar_datos")
    Call<String> putValidarDatos(@Header("Authorization") String sessionToken, @Body Usuario usuario);


    @PUT("autenticacion/validar_datos")
    Call<String> putValidarDatos(@Body Usuario usuario);

    @DELETE("autenticacion/salir")
    Call<String> logoutBackend(@Header("Authorization") String accessToken);

    @POST("ciudadano/ubicacion/reportar")
    Call<Void> postReportarUbicacion(@Header("Authorization") String sessionToken, @Body Ubicacion ubicacion);

}
