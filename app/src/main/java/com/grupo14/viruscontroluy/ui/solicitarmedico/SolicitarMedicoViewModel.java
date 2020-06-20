package com.grupo14.viruscontroluy.ui.solicitarmedico;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolicitarMedicoViewModel extends ViewModel {

    private MutableLiveData<List<String>> sintomasList;
    Map<String,Number> sintomasMap = new HashMap<>();

    public SolicitarMedicoViewModel() {
        this.sintomasList = new MutableLiveData<>();
    }


    public void setSintomasList(List<String> sintomasList) {

        this.sintomasList.setValue(sintomasList);
    }

    public void updateSintomas(List<String> newSintomasList){
        sintomasList.setValue(newSintomasList);
    }

    public LiveData<List<String>> getSintomaList(){


        return sintomasList;
    }

    public void addSintoma(String sintoma){
        this.sintomasList.getValue().add(sintoma);
    }

}
