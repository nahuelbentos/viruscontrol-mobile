package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.reportesUbicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.grupo14.viruscontrol.viruscontroluy.R;

public class ReportarUbicacionActivity extends AppCompatActivity {

    ToggleButton tgbtn;
    ToggleButton tgbtnManual;
    TextView textManual;
    Button buttonManual;
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
    }
}