package com.grupo14.viruscontrol.viruscontroluy.utility;

import javax.inject.Singleton;

@Singleton
public class Utility {
    private String sessionToken;

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
}
