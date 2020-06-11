package com.grupo14.viruscontrol.viruscontroluy.services;

import com.google.gson.annotations.SerializedName;
import com.grupo14.viruscontrol.viruscontroluy.modelos.Usuario;

public class LoginResponse {
    @SerializedName("usuario")
    private Usuario user;
    @SerializedName("sessionToken")
    private String sessionToken;

    public LoginResponse() {
        super();
    }
    public  LoginResponse(Usuario user, String sessionToken) {
        this.user = user;
        this.sessionToken = sessionToken;

    }

    public String getSessionToken() {
        return sessionToken;
    }

    public Usuario getUser() {
        return user;
    }
}
