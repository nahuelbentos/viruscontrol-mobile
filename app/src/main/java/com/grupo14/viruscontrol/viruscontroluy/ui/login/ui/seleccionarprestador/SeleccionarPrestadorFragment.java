package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.seleccionarprestador;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.grupo14.viruscontrol.viruscontroluy.R;
import com.grupo14.viruscontrol.viruscontroluy.modelos.PrestadoraSalud;
import com.grupo14.viruscontrol.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontrol.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirmardatos_solicitarmedico.ConfirmarDatosFragment;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirmardatos_solicitarmedico.ConfirmarDatosViewModel;
import com.grupo14.viruscontrol.viruscontroluy.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SeleccionarPrestadorFragment extends Fragment {

    //private List<String> prestadoresList = new ArrayList<String>();
    private List<String> sintomasPasadosSolicitarMedico = new ArrayList<String>();
    private Spinner prestadoresSpinner;
    private Button btnSiguiente;
    private ListView lvSintomasSeleccionarPrestadorSalud;
    private SeleccionarPrestadorViewModel seleccionarPrestadorViewModel;
    private Map<String,Integer> sintomasMap = new HashMap<>();
    private List<String> prestadoraSaludList;
    private ConfirmarDatosViewModel confirmarDatosViewModel;

    public static SeleccionarPrestadorFragment newInstance() {
        return new SeleccionarPrestadorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View seleccionarPrestadorView = inflater.inflate(R.layout.fragment_seleccionar_prestador, container, false);

        seleccionarPrestadorViewModel = new SeleccionarPrestadorViewModel();

        prestadoresSpinner  = (Spinner) seleccionarPrestadorView.findViewById(R.id.spinner_prestadores_salud);

        //TODO: Obtener prestadores desde servicio REST

        String sessionToken = Utility.getInstance().getSessionToken();

        Call<List<Sintoma>> sintomasCall = ApiAdapter.getApiService().getSintomas(sessionToken);

        sintomasCall.enqueue(new Callback<List<Sintoma>>() {
            @Override
            public void onResponse(Call<List<Sintoma>> call, Response<List<Sintoma>> response) {
                if (!response.isSuccessful()) {
                    Log.v("response", "Code " + response.code());
                    return;
                }

                List<Sintoma> sintomas = response.body();

                for (Sintoma sintoma : sintomas) {
                    Log.v("sintoma",sintoma.getNombre());
                    sintomasMap.put(sintoma.getNombre(),sintoma.getId());

                }

            }

            @Override
            public void onFailure(Call<List<Sintoma>> call, Throwable t) {
                Log.v("response", "fail " + t.getMessage());
            }
        });


        List<String> l = new ArrayList<String>(sintomasMap.keySet());
        Log.v("sintomasMap",sintomasMap.keySet().toString());

        prestadoresSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, l));

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null) {
            sintomasPasadosSolicitarMedico = datosRecuperados.getStringArrayList("sintomasList");
            seleccionarPrestadorViewModel.setSintomasList(sintomasPasadosSolicitarMedico);
            //lvSintomasSeleccionarPrestadorSalud = seleccionarPrestadorView.findViewById(R.id.sintomasPasadosSolicitarMedico);
            //ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, seleccionarPrestadorViewModel.getSintomaList().getValue());
            //lvSintomasSeleccionarPrestadorSalud.setAdapter(adapter);
        }

        btnSiguiente = (Button) seleccionarPrestadorView.findViewById(R.id.btnSiguienteSeleccionarPrestadora);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle datosAEnviar = new Bundle();
                datosAEnviar.putStringArrayList("sintomasList", (ArrayList<String>) sintomasPasadosSolicitarMedico);
                datosAEnviar.putString("prestadoraSalud", prestadoresSpinner.getSelectedItem().toString());
                Fragment nuevoFragmento = ConfirmarDatosFragment.newInstance();
                nuevoFragmento.setArguments(datosAEnviar);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });

        return seleccionarPrestadorView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        seleccionarPrestadorViewModel = ViewModelProviders.of(this).get(SeleccionarPrestadorViewModel.class);
        // TODO: Use the ViewModel
    }

}
