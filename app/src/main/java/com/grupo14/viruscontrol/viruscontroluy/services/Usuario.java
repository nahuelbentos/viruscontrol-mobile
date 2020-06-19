
package com.grupo14.viruscontrol.viruscontroluy.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  Usuario {

    @SerializedName("apellido")
    @Expose
    private String apellido;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("direccion")
    @Expose
    private Object direccion;
    @SerializedName("fechaNacimiento")
    @Expose
    private Object fechaNacimiento;
    @SerializedName("idUsuario")
    @Expose
    private Integer idUsuario;
    @SerializedName("nacionalidad")
    @Expose
    private Object nacionalidad;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("prestadoraSalud")
    @Expose
    private Object prestadoraSalud;
    @SerializedName("primerIngreso")
    @Expose
    private Boolean primerIngreso;
    @SerializedName("sessionToken")
    @Expose
    private String sessionToken;
    @SerializedName("username")
    @Expose
    private String username;

    private String cedula;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Usuario() {
    }

    /**
     * 
     * @param fechaNacimiento
     * @param idUsuario
     * @param direccion
     * @param prestadoraSalud
     * @param nombre
     * @param nacionalidad
     * @param photoUrl
     * @param password
     * @param deleted
     * @param apellido
     * @param correo
     * @param sessionToken
     * @param primerIngreso
     * @param username
     */
    public Usuario(String apellido, String correo, Boolean deleted, Object direccion, Object fechaNacimiento, Integer idUsuario, Object nacionalidad, String nombre, Object password, String photoUrl, Object prestadoraSalud, Boolean primerIngreso, String sessionToken, String username, String cedula) {
        super();
        this.apellido = apellido;
        this.correo = correo;
        this.deleted = deleted;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.idUsuario = idUsuario;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.password = password;
        this.photoUrl = photoUrl;
        this.prestadoraSalud = prestadoraSalud;
        this.primerIngreso = primerIngreso;
        this.sessionToken = sessionToken;
        this.username = username;
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Object getDireccion() {
        return direccion;
    }

    public void setDireccion(Object direccion) {
        this.direccion = direccion;
    }

    public Object getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Object fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Object getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Object nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Object getPrestadoraSalud() {
        return prestadoraSalud;
    }

    public void setPrestadoraSalud(Object prestadoraSalud) {
        this.prestadoraSalud = prestadoraSalud;
    }

    public Boolean getPrimerIngreso() {
        return primerIngreso;
    }

    public void setPrimerIngreso(Boolean primerIngreso) {
        this.primerIngreso = primerIngreso;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCedula(){
        return cedula;
    }

    public void setCedula(String cedula){
        this.cedula = cedula;
    }

}
