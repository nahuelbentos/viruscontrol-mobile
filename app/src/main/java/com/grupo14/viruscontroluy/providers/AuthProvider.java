package com.grupo14.viruscontroluy.providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthProvider {

    FirebaseAuth mAuth;

    public AuthProvider() {
        this.mAuth  = FirebaseAuth.getInstance();
    }

    // Una tarea sería como un observable o promesa
    public String getId(){
        return  mAuth.getCurrentUser().getUid();
    }

    public  boolean existsSession(){
        return (mAuth.getCurrentUser() != null);
    }
}
