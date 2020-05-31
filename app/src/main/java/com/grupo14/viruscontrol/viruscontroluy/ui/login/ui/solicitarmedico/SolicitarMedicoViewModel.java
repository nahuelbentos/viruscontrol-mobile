package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.solicitarmedico;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SolicitarMedicoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<String>> sintomasList;

    public void setSintomasList(MutableLiveData<List<String>> sintomasList) {
        this.sintomasList = sintomasList;
    }

    public LiveData<List<String>> getSintomaList(){
        return sintomasList;
    }
}
