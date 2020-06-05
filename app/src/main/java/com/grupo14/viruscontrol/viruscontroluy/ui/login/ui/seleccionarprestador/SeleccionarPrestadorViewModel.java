package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.seleccionarprestador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo14.viruscontrol.viruscontroluy.modelos.PrestadoraSalud;

import java.util.List;

public class SeleccionarPrestadorViewModel extends ViewModel {
    private MutableLiveData<List<String>> prestadoresList;
    private MutableLiveData<List<String>> sintomasList;
    private MutableLiveData<String> prestadorSeleccionado;

    public SeleccionarPrestadorViewModel() {
        this.prestadoresList = new MutableLiveData<>();
        this.prestadorSeleccionado = new MutableLiveData<>();
        this.sintomasList = new MutableLiveData<>();
    }

    public void setSintomasList(List<String> newSintomasList){
        sintomasList.setValue(newSintomasList);
    }

    public LiveData<List<String>> getSintomaList(){
        return sintomasList;
    }

    public MutableLiveData<String> getPrestadorSeleccionado() {
        return prestadorSeleccionado;
    }

    public void setPrestadorSeleccionado(String prestadorSeleccionado) {
        this.prestadorSeleccionado.setValue(prestadorSeleccionado);
    }

    public LiveData<List<String>> getPrestadoresList() {
        return prestadoresList;
    }

    public void setPrestadoresList(List<String> prestadoresList) {
        this.prestadoresList.setValue(prestadoresList);
    }

}
