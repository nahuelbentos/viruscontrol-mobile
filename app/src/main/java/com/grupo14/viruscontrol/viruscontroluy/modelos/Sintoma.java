package com.grupo14.viruscontrol.viruscontroluy.modelos;

public class Sintoma {
    private Integer id;
    private String nombre;
//    public Sintoma(){
//        super();
//    }
    public Sintoma(Integer id1, String nombre){

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
