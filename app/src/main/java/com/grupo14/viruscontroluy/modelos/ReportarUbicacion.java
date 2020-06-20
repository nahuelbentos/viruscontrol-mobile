package com.grupo14.viruscontroluy.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class ReportarUbicacion {

    @SerializedName("latitud")
    @Expose
    private double latitud;
    @SerializedName("longitud")
    @Expose
    private double longitud;
    @SerializedName("ciudadano")
    @Expose
    private Usuario ciudadano;
    @SerializedName("fecha")
    @Expose
    private Calendar fecha;

    public ReportarUbicacion() {
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public Usuario getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Usuario ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
}
