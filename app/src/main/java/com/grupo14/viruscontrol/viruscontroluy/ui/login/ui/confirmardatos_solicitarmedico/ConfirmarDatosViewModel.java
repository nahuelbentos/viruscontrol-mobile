package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirmardatos_solicitarmedico;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo14.viruscontrol.viruscontroluy.modelos.Medico;
import com.grupo14.viruscontrol.viruscontroluy.modelos.PrestadoraSalud;

import java.util.List;

public class ConfirmarDatosViewModel extends ViewModel {
    private MutableLiveData<Medico> medicoAsignado;
    private MutableLiveData<List<String>> sintomasList;
    private MutableLiveData<PrestadoraSalud> prestadoraSalud;

    public ConfirmarDatosViewModel() {
        medicoAsignado = new MutableLiveData<>();
        sintomasList = new MutableLiveData<>();
        prestadoraSalud = new MutableLiveData<>();
    }

    public ConfirmarDatosViewModel(MutableLiveData<Medico> medicoAsignado, MutableLiveData<List<String>> sintomasList, MutableLiveData<PrestadoraSalud> prestadoraSalud) {
        this.medicoAsignado = medicoAsignado;
        this.sintomasList = sintomasList;
        this.prestadoraSalud = prestadoraSalud;
    }

    public LiveData<Medico> getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado.setValue(medicoAsignado);
    }

    public LiveData<List<String>> getSintomasList() {
        return sintomasList;
    }

    public void setSintomasList(List<String> sintomasList) {
        this.sintomasList.setValue(sintomasList);
    }

    public LiveData<PrestadoraSalud> getPrestadoraSalud() {
        return prestadoraSalud;
    }

    public void setPrestadoraSalud(PrestadoraSalud prestadoraSalud) {
        this.prestadoraSalud.setValue(prestadoraSalud);
    }
}
