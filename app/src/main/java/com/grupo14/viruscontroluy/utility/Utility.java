package com.grupo14.viruscontroluy.utility;

import com.google.firebase.auth.FirebaseUser;
import com.grupo14.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontroluy.modelos.LoginResponse;
import com.grupo14.viruscontroluy.modelos.Token;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class Utility {
    private String sessionToken;
    private LoginResponse loginResponse;
    private List<Sintoma> sintomaList;

    private FirebaseUser firebaseUser;
    private Token pushToken;

    private static Utility instancia;
    private Utility(){}
    public static Utility getInstance(){
        if (instancia == null){
            instancia = new Utility();
        }
        return instancia;
    }

    public Token getPushToken() {
        return pushToken;
    }

    public void setPushToken(Token pushToken) {
        this.pushToken = pushToken;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
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
