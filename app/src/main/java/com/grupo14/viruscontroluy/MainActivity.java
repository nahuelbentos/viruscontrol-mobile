package com.grupo14.viruscontroluy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grupo14.viruscontroluy.modelos.AuthResponse;
import com.grupo14.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontroluy.providers.LoginBackendProvider;
import com.grupo14.viruscontroluy.modelos.LoginResponse;
import com.grupo14.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontroluy.utility.Utility;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final int REQUEST_CODE = 100;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.FacebookBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );
    private LoginBackendProvider mLoginBackendProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginBackendProvider = new LoginBackendProvider(MainActivity.this);
        // Cambia el idioma de los botones
        Locale localizacion = new Locale("es", "ES");

        Locale.setDefault(localizacion);
        Configuration config = new Configuration();
        config.locale = localizacion;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){

                    String Name = user.getDisplayName();
                    String[] splited = Name.split("\\s+");

//                    if(1==0) {

                        mLoginBackendProvider.loginBackend(splited[0], splited[1], user.getEmail(), user.getEmail()).enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                if (response.isSuccessful()) {

                                    Log.d("onResponse", "onResponse: response: " + response.toString());
                                    Log.d("onResponse", "onResponse: getResponse: " + response.body().getResponse().toString());
                                    //                                Log.d("onResponse", "onResponse: getSessionToken: " + response.body().getSessionToken().toString());
                                    Log.d("onResponse", "onResponse: response.getNombre: " + response.body().getUsuario().getNombre().toString());
                                    Log.d("onResponse", "onResponse: response.getApellido: " + response.body().getUsuario().getApellido().toString());
                                    Log.d("onResponse", "onResponse: response.getPrimerIngreso: " + response.body().getUsuario().getPrimerIngreso().toString());
                                    Log.d("onResponse", "onResponse: response.getPrimerIngreso: " + response.body().getUsuario().getPrimerIngreso().toString());

                                    Call<List<Sintoma>> sintomasCall = ApiAdapter.getApiService().getSintomas(response.body().getSessionToken());


                                    sintomasCall.enqueue(new Callback<List<Sintoma>>() {
                                        @Override
                                        public void onResponse(Call<List<Sintoma>> call, Response<List<Sintoma>> response) {
                                            if (!response.isSuccessful()) {
                                                Log.v("response", "Code " + response.code());
                                                return;
                                            }

                                            List<Sintoma> sintomas = response.body();

                                            if (sintomas != null) {
                                                Log.v("Sintomas", "Sintomas on LoginActivity"+ sintomas.toString());
                                                Utility.getInstance().setSintomaList(sintomas);
                                            }


                                        }

                                        @Override
                                        public void onFailure(Call<List<Sintoma>> call, Throwable t) {
                                            Log.v("response", "fail " + t.getMessage());
                                        }
                                    });

                                    Utility.getInstance().setSessionToken(response.body().getSessionToken());
                                    Utility.getInstance().setLoginResponse(response.body());


                                    boolean primerIngreso = response.body().getUsuario().getPrimerIngreso();

                                    Intent intent;
                                    if (response.body().getResponse().equals(AuthResponse.PRIMERINGRESO)) {

                                        Toast.makeText(MainActivity.this, "Voy al primer inicio", Toast.LENGTH_SHORT).show();
                                        //                                    intent = new Intent(MainActivity.this, PrimerInicioActivity.class);
                                        //                                    startActivity(intent);

                                    } else {
                                        intent = new Intent(MainActivity.this, MenuActivity.class);

                                        startActivity(intent);
                                    }

                                    Toast.makeText(MainActivity.this, "El login fue exitoso", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(MainActivity.this, "El login no fue exitoso", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Log.d("errorOnResponse", "onResponse: error: " + t.getMessage());

                            }
                        });
//                    }else{
//
//                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
//
//                        startActivity(intent);
//
//                    }


                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setIsSmartLockEnabled(false)
                                    .build(), REQUEST_CODE
                    );
                }
            }
        };

    } // Fin de onCreate


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }

    public void cerrarSesion(View view) {

        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "Sesión cerrada!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}