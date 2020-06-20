
package com.grupo14.viruscontrol.viruscontroluy.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmarVisitaResponse {

    @SerializedName("idVisita")
    @Expose
    private Integer idVisita;
    @SerializedName("medicoId")
    @Expose
    private Integer medicoId;
    @SerializedName("medicoNomApe")
    @Expose
    private String medicoNomApe;
    @Expose
    @SerializedName("fecha")
    private String fecha;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ConfirmarVisitaResponse() {
    }

    /**
     * 
     * @param fecha
     * @param medicoNomApe
     * @param medicoId
     * @param idVisita
     */
    public ConfirmarVisitaResponse(Integer idVisita, Integer medicoId, String medicoNomApe, String fecha) {
        super();
        this.idVisita = idVisita;
        this.medicoId = medicoId;
        this.medicoNomApe = medicoNomApe;
        this.fecha = fecha;
    }

    public Integer getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Integer idVisita) {
        this.idVisita = idVisita;
    }

    public ConfirmarVisitaResponse withIdVisita(Integer idVisita) {
        this.idVisita = idVisita;
        return this;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public ConfirmarVisitaResponse withMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
        return this;
    }

    public String getMedicoNomApe() {
        return medicoNomApe;
    }

    public void setMedicoNomApe(String medicoNomApe) {
        this.medicoNomApe = medicoNomApe;
    }

    public ConfirmarVisitaResponse withMedicoNomApe(String medicoNomApe) {
        this.medicoNomApe = medicoNomApe;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ConfirmarVisitaResponse withFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

}
