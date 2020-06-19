package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirmardatos_solicitarmedico;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo14.viruscontrol.viruscontroluy.modelos.PrestadoraSalud;

import java.util.List;

public class ConfirmarDatosViewModel extends ViewModel {
    private MutableLiveData<List<String>> sintomasList;
    private MutableLiveData<PrestadoraSalud> prestadoraSalud;

    public ConfirmarDatosViewModel() {
        sintomasList = new MutableLiveData<>();
        prestadoraSalud = new MutableLiveData<>();
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
