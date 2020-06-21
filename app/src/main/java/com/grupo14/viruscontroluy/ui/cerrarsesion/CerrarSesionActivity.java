package com.grupo14.viruscontroluy.ui.cerrarsesion;

import android.app.AlertDialog;

import androidx.appcompat.app.ActionBar;
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

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CerrarSesionActivity extends AppCompatActivity {

    private LoginBackendProvider mLoginBackendProvider;
    private FirebaseAuth mFirebaseAuth;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);


        ActionBar toolbar = getSupportActionBar();

        getSupportActionBar().setTitle("Cerrar sesión");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDialog = new SpotsDialog.Builder().setContext(CerrarSesionActivity.this).setMessage("Espere un momento").build();
        mLoginBackendProvider = new LoginBackendProvider(CerrarSesionActivity.this);

        String accessToken = Utility.getInstance().getSessionToken();
        Log.v("LogoutBackend", "LogoutBackend :::  accessToken " + accessToken);
        Call<String> logoutBackend = ApiAdapter.getApiService().logoutBackend(accessToken);




        mDialog.show();
        mLoginBackendProvider.logoutBackend(accessToken).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){

                    mFirebaseAuth = FirebaseAuth.getInstance();
                    mFirebaseAuth.signOut();

                    Intent i = new Intent(CerrarSesionActivity.this, MainActivity.class);
                    startActivity(i);
                    mDialog.dismiss();
                    Toast.makeText(CerrarSesionActivity.this, "Se cierra sesion " , Toast.LENGTH_SHORT).show();
                }else{
                    Log.v("LogoutBackend", "LogoutBackend ::: response "+ "message " + response.message());
                    Log.v("LogoutBackend", "LogoutBackend ::: response "+ "body " + response.body());
                    Log.v("LogoutBackend", "LogoutBackend ::: response "+ "Code " + response.code());
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                if(t.getCause() != null){
                    Log.v("LogoutBackend","LogoutBackend ::: 2)Fail : " + t.getCause().toString());
                }

                Log.v("LogoutBackend","LogoutBackend ::: 3)Fail : " + t.getMessage());
                Log.v("LogoutBackend","LogoutBackend ::: 4)Fail : " + t.getLocalizedMessage());
                Log.v("LogoutBackend","LogoutBackend ::: 5)call : " + call.toString());


            }
        });



    }
}