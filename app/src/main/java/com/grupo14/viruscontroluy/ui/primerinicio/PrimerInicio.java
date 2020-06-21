package com.grupo14.viruscontroluy.ui.primerinicio;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo14.viruscontroluy.R;
import com.grupo14.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontroluy.utility.Utility;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrimerInicio extends AppCompatActivity {

    //elementos de pantalla
    private Button btnConfirmar;
    private TextView mDisplayDate;
    private EditText etUser;
    private EditText etEmail;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etCedula;
    private EditText etTelefono;
    private Spinner spNacionalidad;
    private TextView etFechaNac;
    private TextView etDireccion;

    //selector de fecha
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String cedula;
    private String telefono;
    private String nacionalidad;
    private Object fechaNac;
    private String direccion;
    private Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_primer_inicio);
/*
        //Obtener elementos de pantalla de login
        Bundle datosUsuario = this.getIntent().getExtras();

        btnConfirmar = (Button) findViewById(R.id.btnConfirmarPirmerInicio);
        etUser = (EditText) findViewById(R.id.etUsuarioPrimerInicio);
        etEmail = (EditText) findViewById(R.id.etEmailPrimerInicio);
        etNombre = (EditText) findViewById(R.id.etNombrePrimerInicio);
        etApellido = (EditText) findViewById(R.id.etApellidoPrimerInicio);
        etCedula = (EditText) findViewById(R.id.etCedulaPrimerInicio);
        etTelefono = (EditText) findViewById(R.id.etTelefonoPrimerInicio);
        spNacionalidad = (Spinner) findViewById(R.id.spinnerNacionalidadPrimerInicio);
        etDireccion = (EditText) findViewById(R.id.etDireccionPrimerInicio);

        if(datosUsuario !=null){
            etUser.setText(datosUsuario.getString("username"));
            etEmail.setText(datosUsuario.getString("email"));
            etNombre.setText(datosUsuario.getString("nombre"));
            etApellido.setText(datosUsuario.getString("apellido"));



        }

        //El siguietne TextView corresponde a la fecha de nacimiento
        mDisplayDate = (TextView) findViewById(R.id.tvFechaNacimientoPrimerInicio);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtengo la fecha
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PrimerInicio.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                System.out.println("onDateSet:  mm/dd/yyy: " + month + "/" + dayOfMonth + "/" + year);

                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.v("Username primer inicio::: ", Utility.getInstance().getLoginResponse().getUsuario().getIdUsuario().toString());

                Intent i = new Intent(PrimerInicio.this, MenuUsuarioCiudadanoActivity.class);
                Log.v("Usuario primer inicio::: ", Utility.getInstance().getLoginResponse().getUsuario().getUsername());
                Utility.getInstance().getLoginResponse().getUsuario().setCedula(etCedula.getText().toString());
                //Utility.getInstance().getLoginResponse().getUsuario().setNacionalidad("Uruguay");
                Utility.getInstance().getLoginResponse().getUsuario().setFechaNacimiento(mDisplayDate.getText().toString());
                Utility.getInstance().getLoginResponse().getUsuario().setDireccion(etDireccion.getText().toString());
                //Utility.getInstance().getLoginResponse().getUsuario().setSessionToken(datosUsuario.getString("token"));
                //Utility.getInstance().getLoginResponse().getUsuario().setIdUsuario(Integer.parseInt(datosUsuario.getString("idUsuario")));
                Utility.getInstance().getLoginResponse().getUsuario().setCorreo(etEmail.getText().toString());
                Utility.getInstance().getLoginResponse().getUsuario().setNombre(etNombre.getText().toString());
                Utility.getInstance().getLoginResponse().getUsuario().setApellido(etApellido.getText().toString());


                //TODO aqui hacer el PUT con el usuario logueado



                Call<String> callBackendValidarDatos = ApiAdapter.getApiService().putValidarDatos(Utility.getInstance().getSessionToken(), Utility.getInstance().getLoginResponse().getUsuario());
                callBackendValidarDatos.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (!response.isSuccessful()) {
                            Log.v("response", "Code " + response.code());
                            return;
                        }
                        Toast.makeText(getApplicationContext(), "Datos actualizados exitosamente " , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

                startActivity(i);
            }
        });

*/
    }
}