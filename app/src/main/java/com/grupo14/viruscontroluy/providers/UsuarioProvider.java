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


    public Task<Void> update(Usuario usuario){
        Log.d("ErrorUpdate", "update: " + usuario);

            /* Lista de campos

        Fotito:
        Usuario:
        Email:
        Nombre:
        Apellido:
        CI:
        Telefono:
        Direccion:

        */

        Map<String, Object> map = new HashMap<>();
        map.put("usuario", usuario.getCorreo());
        map.put("correo", usuario.getCorreo());
        map.put("nombre", usuario.getNombre());
        map.put("apellido", usuario.getApellido());
        map.put("cedula", usuario.getDocumento());
        map.put("telefono", usuario.getTelefono());
        map.put("direccion", usuario.getDireccion());




        Log.d("ErrorUpdate", "usuario.getNombre(): " + usuario.getNombre());
        Log.d("ErrorUpdate", "usuario.getApellido(): " + usuario.getApellido());
        Log.d("ErrorUpdate", "usuario.getTelefono(): " + usuario.getTelefono());

        return database.child(usuario.getUIdFirebase()).updateChildren(map);
    }

}
