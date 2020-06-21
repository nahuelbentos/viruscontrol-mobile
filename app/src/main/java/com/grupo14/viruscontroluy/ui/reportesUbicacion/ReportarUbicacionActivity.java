package com.grupo14.viruscontroluy.ui.reportesUbicacion;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.grupo14.viruscontroluy.R;
import com.grupo14.viruscontroluy.modelos.Ubicacion;
import com.grupo14.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontroluy.utility.Utility;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportarUbicacionActivity extends AppCompatActivity {

    private final static int LOCATION_REQUEST_CODE = 1;
    private final static int SETTINGS_REQUEST_CODE = 2;

    private FusedLocationProviderClient mFusedLocation;
    private LocationRequest mLocationRequest;

    private LatLng mCurrentLatLng;

    ToggleButton tgbtn;
    ToggleButton tgbtnManual;
    TextView textManual;
    Button buttonManual;

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Log.d("ErrorLocation", "onLocationResult arranca  " );
            super.onLocationResult(locationResult);
            for (Location location : locationResult.getLocations()) {
                if (getApplicationContext() != null) {

                    Log.d("ErrorLocation", "onLocationResult: long "+ location.getLongitude());
                    Log.d("ErrorLocation", "onLocationResult: lati "+ location.getLatitude());
//                    Toast.makeText(this, "Latitud: " + String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "long: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    mCurrentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                } else{


                    Log.d("ErrorLocation", "getApplicationContext() es null " );
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_ubicacion);

        tgbtn = (ToggleButton) findViewById(R.id.tgbtn1);
        tgbtnManual = (ToggleButton) findViewById(R.id.tgbtn2);
        textManual = findViewById(R.id.textManual);
        buttonManual = findViewById(R.id.buttonManual);




    }

    public void onclick(View view) {

        if(tgbtn.isChecked()){

            mFusedLocation = LocationServices.getFusedLocationProviderClient(this);

            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(1000);
            mLocationRequest.setFastestInterval(1000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setSmallestDisplacement(5);


            startLocation();
        }

        if(tgbtnManual.getVisibility() == View.INVISIBLE) {
            tgbtnManual.setVisibility(View.VISIBLE);
            textManual.setVisibility(View.VISIBLE);
        }
        else{
            tgbtnManual.setVisibility(View.INVISIBLE);
            if(tgbtnManual.isChecked())
                tgbtnManual.toggle();
            textManual.setVisibility(View.INVISIBLE);
            buttonManual.setVisibility(View.INVISIBLE);
        }
    }

    public void onclickManual(View view) {
        if(buttonManual.getVisibility() == View.INVISIBLE)
            buttonManual.setVisibility(View.VISIBLE);
        else
            buttonManual.setVisibility(View.INVISIBLE);
    }

    public void reportarUbicacionManual(View view) {

        Toast.makeText(this, "Reportaste ubicación", Toast.LENGTH_SHORT).show();
        if(mCurrentLatLng != null){
            Toast.makeText(this, "Latitud: " + mCurrentLatLng.latitude, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "longitude: " + mCurrentLatLng.longitude, Toast.LENGTH_SHORT).show();

        }
        Ubicacion ubicacion = new Ubicacion();
        String latitud = String.valueOf(mCurrentLatLng.latitude);
        ubicacion.setLatitud(latitud);
        Log.v("ubicacion", "latitude " + latitud);
        String longitud = String.valueOf(mCurrentLatLng.longitude);
        ubicacion.setLongitud(longitud);
        Log.v("ubicacion", "longitude " + longitud);
        Call<Void> callReportarUbicacion = ApiAdapter.getApiService().postReportarUbicacion(Utility.getInstance().getSessionToken(),ubicacion);
        Toast.makeText(this, "prueba", Toast.LENGTH_SHORT);
        callReportarUbicacion.enqueue(new Callback<Void>(){

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.v("response", "Code " + response.code());
                    return;
                }
                Log.v("response", "Afuera del if " + response.code());
                Toast.makeText(getApplicationContext(), "Ubicación enviada con éxito." , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.v("response", "fail " + t.getMessage());

            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (gpsActived()){
                        Log.d("ErrorLocation", " levanto instancia: ");
                        mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                    } else {
                        Log.d("ErrorLocation", " gps inactivo: ");
                        showAlertDialogNOGPS();
                    }
                } else {
                    Log.d("ErrorLocation", " no tiene permisos 1: ");
                    checkLocationPermissions();
                }
            } else {
                Log.d("ErrorLocation", " no tiene permisos 2: ");
                checkLocationPermissions();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ErrorLocation", "estoy en onActivityResult: ");
        Log.d("ErrorLocation", "gps activo : "+ gpsActived());
        Log.d("ErrorLocation", "gps requestCode : "+ requestCode);
        if (requestCode == SETTINGS_REQUEST_CODE && gpsActived()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        } else {
            showAlertDialogNOGPS();
        }
    }

    private void showAlertDialogNOGPS(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Por favor activa el GPS para continuar")
                .setPositiveButton("Configuraciones", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), SETTINGS_REQUEST_CODE);
                    }
                })
                .create()
                .show();
    }

    private boolean gpsActived(){
        boolean isActive = false;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Si tiene el gps activado
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            isActive = true;
        }
        return isActive;
    }



    private void disconnect(){
        if(mFusedLocation != null){

            mFusedLocation.removeLocationUpdates(mLocationCallback);
//            if(mAuthProvider.existsSession()){
//                mGeofireProvider.removeLocation(mAuthProvider.getId());
//            }
        } else {
            Toast.makeText(this, "No te puedes desconectar", Toast.LENGTH_SHORT).show();
        }

    }

    private void startLocation(){
        Log.d("ErrorLocation", "entro en startLocation: ");
        Log.d("ErrorLocation", "entro en gpsActived(): " + gpsActived());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Log.d("ErrorLocation", "    1: " + gpsActived());
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Log.d("ErrorLocation", "    2: " + gpsActived());
                if (gpsActived()){
            Log.d("ErrorLocation", "    3: " + gpsActived());
                    mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                    Log.d("ErrorLocation", "    mLocationCallback 3: " + mLocationCallback.toString());
                } else {
                    showAlertDialogNOGPS();
                }
            } else {
                checkLocationPermissions();
            }
        } else {
            if (gpsActived()){
                mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            } else {
                showAlertDialogNOGPS();
            }
        }
    }

    private void checkLocationPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                new AlertDialog.Builder(this)
                        .setTitle("Proporciona los permisos para continuar")
                        .setMessage("Esta aplicación requiere de los permisos de ubicación para poder utilizarse")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Habilita los permisos para obtener la ubicación del celular
                                ActivityCompat.requestPermissions(ReportarUbicacionActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
                            }
                        })
                        .create()
                        .show();

            } else {
                ActivityCompat.requestPermissions(ReportarUbicacionActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }

    }

}