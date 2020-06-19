package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirmardatos_solicitarmedico;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.grupo14.viruscontrol.viruscontroluy.R;
import com.grupo14.viruscontrol.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontrol.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontrol.viruscontroluy.services.ConfirmarVisitaResponse;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.home.HomeFragment;
import com.grupo14.viruscontrol.viruscontroluy.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmarDatosFragment extends Fragment {

    private ConfirmarDatosViewModel mViewModel;
    private String prestadoraSalud;
    private List<String> sintomasList;
    private ListView lvSintomasListConfirmarDatos;
    private Button confirmarVisitaSolicitarMedico;



    public static ConfirmarDatosFragment newInstance() {
        return new ConfirmarDatosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View confirmarDatosView;
        confirmarDatosView = inflater.inflate(R.layout.fragment_confirmar_datos, container, false);

        mViewModel = new ConfirmarDatosViewModel();

        Bundle datosRecuperados = getArguments();

        if (datosRecuperados != null) {
            sintomasList = datosRecuperados.getStringArrayList("sintomasList");
            //prestadoraSalud = datosRecuperados.getString("prestadoraSalud");
            //tvPrestadoraSaludConfirmar.setText(prestadoraSalud);
            mViewModel.setSintomasList(sintomasList);


            lvSintomasListConfirmarDatos = confirmarDatosView.findViewById(R.id.sintomasIngresadosConfirmarDatos);
            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mViewModel.getSintomasList().getValue());
            lvSintomasListConfirmarDatos.setAdapter(adapter);
        }

        confirmarVisitaSolicitarMedico = confirmarDatosView.findViewById(R.id.btnConfirmarVisita);
        confirmarVisitaSolicitarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Hacer POST para enviar los datos
                Map<String,Integer> mapSintomas = new HashMap<>();
                List<Sintoma> sintomasSeleccionados = new ArrayList<>();
                List<Sintoma> sintomasBackend = Utility.getInstance().getSintomaList();
                for(Sintoma sintoma : sintomasBackend){
                    mapSintomas.put(sintoma.getNombre(),sintoma.getId());
                }

                for(String nombreSintoma : sintomasList){
                    if(mapSintomas.containsKey(nombreSintoma)){
                        Sintoma s = new Sintoma(mapSintomas.get(nombreSintoma), nombreSintoma);
                        sintomasSeleccionados.add(s);
                    }
                }


                Call<ConfirmarVisitaResponse> callSolicitarMedico = ApiAdapter.getApiService().postSolicitarVisita(Utility.getInstance().getSessionToken(),sintomasSeleccionados);
                callSolicitarMedico.enqueue(new Callback<ConfirmarVisitaResponse>(){

                    @Override
                    public void onResponse(Call<ConfirmarVisitaResponse> call, Response<ConfirmarVisitaResponse> response) {
                        if (!response.isSuccessful()) {
                            Log.v("response", "Code " + response.code());
                            return;
                        }
                        Toast.makeText(getActivity(), "Visita solicitada exitosamente." , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ConfirmarVisitaResponse> call, Throwable t) {
                        Log.v("response", "fail " + t.getMessage());

                    }
                });

                Fragment nuevoFragmento = new HomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });

        return confirmarDatosView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConfirmarDatosViewModel.class);
        // TODO: Use the ViewModel
    }

}
