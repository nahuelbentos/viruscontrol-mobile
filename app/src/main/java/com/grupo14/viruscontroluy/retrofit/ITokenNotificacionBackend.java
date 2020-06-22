package com.grupo14.viruscontroluy.retrofit;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ITokenNotificacionBackend {

    @POST("/salir/")
    Call<Void> logoutBackend(@Header("authorization") String accessToken);
}
