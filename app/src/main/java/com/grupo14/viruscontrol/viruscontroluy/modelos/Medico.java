package com.grupo14.viruscontrol.viruscontroluy.modelos;


import java.io.Serializable;
import java.util.Calendar;

public class Medico extends Usuario implements Serializable {
    private static final long serialVersionUID = 2198638793079583112L;

    private PrestadoraSalud prestadoraSalud;

    private boolean conectado;

    public Medico() {
        super();
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public Medico(int idUsuario, String nombre, String apellido, String direccion, Calendar fechaNacimiento,
                  String nacionalidad, String correo, String username, String password, boolean primerIngreso, PrestadoraSalud prestadoraSalud) {
        super(idUsuario, nombre, apellido, direccion, fechaNacimiento, nacionalidad, correo, username, password, primerIngreso);
        this.prestadoraSalud = prestadoraSalud;
    }

    public PrestadoraSalud getPrestadoraSalud() {
        return prestadoraSalud;
    }

    public void setPrestadoraSalud(PrestadoraSalud prestadoraSalud) {
        this.prestadoraSalud = prestadoraSalud;
    }




}
