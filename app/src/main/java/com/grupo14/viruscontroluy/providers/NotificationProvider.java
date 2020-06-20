package com.grupo14.viruscontroluy.providers;

import com.grupo14.viruscontroluy.modelos.FCMBody;
import com.grupo14.viruscontroluy.modelos.FCMResponse;
import com.grupo14.viruscontroluy.retrofit.IFCMApi;
import com.grupo14.viruscontroluy.retrofit.RetrofitClient;

import retrofit2.Call;

public class NotificationProvider {
    private String url = "https://fcm.googleapis.com";

    public NotificationProvider() {
    }

    public Call<FCMResponse> sendNotification(FCMBody body){
        return RetrofitClient.getClientObject(url).create(IFCMApi.class).send(body);
    }
}
