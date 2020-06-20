
package com.grupo14.viruscontroluy.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("sessionToken")
    @Expose
    private String sessionToken;
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginResponse() {
    }

    /**
     * 
     * @param response
     * @param sessionToken
     * @param usuario
     */
    public LoginResponse(String response, String sessionToken, Usuario usuario) {
        super();
        this.response = response;
        this.sessionToken = sessionToken;
        this.usuario = usuario;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
