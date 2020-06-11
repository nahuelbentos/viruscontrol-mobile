package com.grupo14.viruscontrol.viruscontroluy.modelos;

public class Sintoma {
    private String id;
    private String nombre;
//    public Sintoma(){
//        super();
//    }
    public Sintoma(String id1, String nombre){

        this.id = id1;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
