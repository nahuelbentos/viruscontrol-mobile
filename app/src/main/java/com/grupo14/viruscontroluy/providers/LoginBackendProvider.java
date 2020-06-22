package com.grupo14.viruscontroluy.providers;

import android.content.Context;

import com.grupo14.viruscontroluy.modelos.Usuario;
import com.grupo14.viruscontroluy.retrofit.ILoginBackend;
import com.grupo14.viruscontroluy.retrofit.RetrofitClient;
import com.grupo14.viruscontroluy.modelos.LoginRequest;
import com.grupo14.viruscontroluy.modelos.LoginResponse;

import retrofit2.Call;

public class LoginBackendProvider {


    private Context context;

    public LoginBackendProvider(Context context) {
        this.context = context;
    }

    public Call<LoginResponse> loginBackend(String nombre, String apellido, String email, String username ) {
        LoginRequest body = new LoginRequest(nombre, apellido, email, username);

        // Hago el request el servicio de Google usando la interfaz como referencia.
         return RetrofitClient.iniciarSesionBackend().create(ILoginBackend.class).loginBackend(body);
    }


    public Call<Void> logoutBackend(String token ) {


        // Hago el request el servicio de Google usando la interfaz como referencia.
        return RetrofitClient.cerrarSesionBackend().create(ILoginBackend.class).logoutBackend(token);
    }

    public Call<Void> validarDatosBackend(Usuario usuario) {


        // Hago el request el servicio de Google usando la interfaz como referencia.
        return RetrofitClient.validarDatosBackend().create(ILoginBackend.class).validarDatosBackend(usuario);
    }

    public Call<Void> setPushToken(String sessionToken, String pushToken) {


        // Hago el request el servicio de Google usando la interfaz como referencia.
        return RetrofitClient.setPushToken().create(ILoginBackend.class).setPushToken("text/plain", sessionToken, pushToken);
    }

}
