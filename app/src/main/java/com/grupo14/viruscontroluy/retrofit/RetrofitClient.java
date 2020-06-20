package com.grupo14.viruscontroluy.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RetrofitClient {
//    private static Retrofit retrofit = null;

    private static String baseUrl = "https://viruscontroluy.xyz:8443/viruscontrol-web/rest/";

    public static Retrofit getClient(String url) {
//        if (retrofit == null) {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

        return  retrofit;
    }

    public static Retrofit getClientObject(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return  retrofit;
    }

    public static Retrofit iniciarSesionBackend(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;


    }

    public static Retrofit cerrarSesionBackend(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;


    }


}
