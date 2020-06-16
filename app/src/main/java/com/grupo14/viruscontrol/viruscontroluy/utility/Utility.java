package com.grupo14.viruscontrol.viruscontroluy.utility;

import com.grupo14.viruscontrol.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontrol.viruscontroluy.services.LoginResponse;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class Utility {
    private String sessionToken;
    private LoginResponse loginResponse;
    private List<Sintoma> sintomaList;

    private static Utility instancia;
    private Utility(){}
    public static Utility getInstance(){
        if (instancia == null){
            instancia = new Utility();
        }
        return instancia;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public List<Sintoma> getSintomaList() {
        return sintomaList;
    }

    public void setSintomaList(List<Sintoma> sintomaList) {
        this.sintomaList = sintomaList;
    }
}
