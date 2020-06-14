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


        String sessionToken = Utility.getInstance().getSessionToken();
        Map<String,Number> sintomasMap = new HashMap<>();


        Call<List<Sintoma>> sintomasCall = ApiAdapter.getApiService().getSintomas(sessionToken);


        sintomasCall.enqueue(new Callback<List<Sintoma>>() {
            @Override
            public void onResponse(Call<List<Sintoma>> call, Response<List<Sintoma>> response) {
                if (!response.isSuccessful()) {
                    Log.v("response", "Code " + response.code());
                    return;
                }

                List<Sintoma> sintomas = response.body();

                if (sintomas != null) {
                    for (Sintoma sintoma : sintomas) {
                        Log.v("sintoma",sintoma.getNombre());
                        sintomasMap.put(sintoma.getNombre(),sintoma.getId());

                    }
                }

            }

            @Override
            public void onFailure(Call<List<Sintoma>> call, Throwable t) {
                Log.v("response", "fail " + t.getMessage());
            }
        });

        List<String> listaAux = new ArrayList<>();
        listaAux = (List<String>) sintomasMap.keySet();

        sintomasList.setValue(listaAux);


        return sintomasList;
    }

    public void addSintoma(String sintoma){
        this.sintomasList.getValue().add(sintoma);
    }

}
