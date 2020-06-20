package com.grupo14.viruscontroluy.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {
    private static VirusControlService API_SERVICE;

    public static VirusControlService getApiService() {


        String baseUrl = "https://viruscontroluy.xyz:8443/viruscontrol-web/rest/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE = retrofit.create(VirusControlService.class);
        }

        return API_SERVICE;
    }
}
