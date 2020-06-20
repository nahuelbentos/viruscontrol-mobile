package com.grupo14.viruscontroluy.ui.cerrarsesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.grupo14.viruscontroluy.MainActivity;
import com.grupo14.viruscontroluy.R;
import com.grupo14.viruscontroluy.providers.LoginBackendProvider;
import com.grupo14.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontroluy.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CerrarSesionActivity extends AppCompatActivity {

    private LoginBackendProvider mLoginBackendProvider;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);



        mLoginBackendProvider = new LoginBackendProvider(CerrarSesionActivity.this);

        String accessToken = Utility.getInstance().getSessionToken();
        Log.v("LogoutBackend", "LogoutBackend :::  accessToken " + accessToken);
        Call<String> logoutBackend = ApiAdapter.getApiService().logoutBackend(accessToken);




        mLoginBackendProvider.logoutBackend(accessToken).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){

                    mFirebaseAuth = FirebaseAuth.getInstance();
                    mFirebaseAuth.signOut();

                    Intent i = new Intent(CerrarSesionActivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(CerrarSesionActivity.this, "Se cierra sesion " , Toast.LENGTH_SHORT).show();
                }else{
                    Log.v("LogoutBackend", "LogoutBackend ::: response "+ "message " + response.message());
                    Log.v("LogoutBackend", "LogoutBackend ::: response "+ "body " + response.body());
                    Log.v("LogoutBackend", "LogoutBackend ::: response "+ "Code " + response.code());
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("LogoutBackend","LogoutBackend ::: Fail "+ "Code " + t.getMessage());


            }
        });

    }
}