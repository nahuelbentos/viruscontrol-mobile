package com.grupo14.viruscontroluy.providers;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.grupo14.viruscontroluy.modelos.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UsuarioProvider {



    DatabaseReference database;

    public UsuarioProvider() {
        this.database = FirebaseDatabase.getInstance().getReference().child("Usarios");
    }

    public Task<Void> create(Usuario usuario){
        return database.child(usuario.getUIdFirebase()).setValue(usuario);
    }

    public DatabaseReference getUsuario(String idUsuario){
        return database.child(idUsuario);
    }



}
