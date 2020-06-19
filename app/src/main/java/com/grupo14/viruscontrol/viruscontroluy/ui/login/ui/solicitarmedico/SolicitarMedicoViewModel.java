package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.solicitarmedico;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo14.viruscontrol.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontrol.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontrol.viruscontroluy.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
