package com.grupo14.viruscontrol.viruscontroluy.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sintoma {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    public Sintoma(){
    }
    public Sintoma(Integer id1, String nombre){
        super();
        this.id = id1;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
