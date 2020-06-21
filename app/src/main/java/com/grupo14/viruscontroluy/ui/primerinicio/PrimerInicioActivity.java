package com.grupo14.viruscontroluy.ui.primerinicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.grupo14.viruscontroluy.MainActivity;
import com.grupo14.viruscontroluy.MenuActivity;
import com.grupo14.viruscontroluy.R;
import com.grupo14.viruscontroluy.includes.MyToolbar;
import com.grupo14.viruscontroluy.modelos.Usuario;
import com.grupo14.viruscontroluy.providers.AuthProvider;
import com.grupo14.viruscontroluy.providers.LoginBackendProvider;
import com.grupo14.viruscontroluy.providers.UsuarioProvider;
import com.grupo14.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontroluy.ui.cerrarsesion.CerrarSesionActivity;
import com.grupo14.viruscontroluy.utility.Utility;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrimerInicioActivity extends AppCompatActivity {


//    Usuario:
//    Email:
//    Nombre:
//    Apellido:
//    CI:
//    Telefono:
//    Direccion:

    private TextInputEditText mTextInputUsuario;
    private TextInputEditText mTextInputEmail;
    private TextInputEditText mTextInputNombre;
    private TextInputEditText mTextInputApellido;
    private TextInputEditText mTextInputCI;
    private TextInputEditText mTextInputTelefono;
    private TextInputEditText mTextInputDireccion;

    private Button mButtonConfirmarDatos;
    private Button mButtonCancelar;
    private AlertDialog mDialog;
    private LoginBackendProvider mLoginBackendProvider;
    private UsuarioProvider mUsuarioProvider;
    private AuthProvider mAuthProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer_inicio);

//        MyToolbar.show(this, "Perfil de Usuario", true);
        ActionBar toolbar = getSupportActionBar();

        getSupportActionBar().setTitle("Perfil de Usuario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextInputUsuario = findViewById(R.id.textInputUsername);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputNombre = findViewById(R.id.textInputNombre);
        mTextInputApellido = findViewById(R.id.textInputApellido);
        mTextInputCI = findViewById(R.id.textInputCedulaIdentidad);
        mTextInputTelefono = findViewById(R.id.textInputTelefono);
        mTextInputDireccion = findViewById(R.id.textInputDireccion);

        mButtonConfirmarDatos = findViewById(R.id.btnConfirmarDatos);
        mButtonCancelar = findViewById(R.id.btnCancelarDatos);


        mDialog = new SpotsDialog.Builder().setContext(PrimerInicioActivity.this).setMessage("Modificando el usuario").build();

        mLoginBackendProvider = new LoginBackendProvider(PrimerInicioActivity.this);

        mUsuarioProvider = new UsuarioProvider();
        mAuthProvider = new AuthProvider();

        mButtonConfirmarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarDatos();
            }
        });

        mButtonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrimerInicioActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


    }

    private void confirmarDatos() {
         /* Lista de campos

        Fotito:
        Usuario:
        Email:
        Nombre:
        Apellido:
        CI:
        Telefono:
        Direccion:

         Usuario(String apellido, String correo, String direccion, String nombre, String username, String cedula, String telefono)
        */

         String username = mTextInputUsuario.getText().toString();
         String correo = mTextInputEmail.getText().toString();
         String nombre = mTextInputNombre.getText().toString();
         String apellido = mTextInputApellido.getText().toString();
         String cedula = mTextInputCI.getText().toString();
         String telefono = mTextInputTelefono.getText().toString();
         String direccion = mTextInputDireccion.getText().toString();

         Usuario usuario = new Usuario(apellido,correo, direccion, nombre, username, cedula, telefono);





        mDialog.show();
        mLoginBackendProvider.validarDatosBackend(usuario).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){

                    // Modifico el usuario en Firebase.
                    usuario.setUIdFirebase(mAuthProvider.getId());

                    mUsuarioProvider.update(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Intent i = new Intent(PrimerInicioActivity.this, MainActivity.class);
                            startActivity(i);
                            mDialog.dismiss();
                        }
                    });

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