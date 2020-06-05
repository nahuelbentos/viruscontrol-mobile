package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.seleccionarprestador;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.grupo14.viruscontrol.viruscontroluy.R;
import com.grupo14.viruscontrol.viruscontroluy.modelos.PrestadoraSalud;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirmardatos_solicitarmedico.ConfirmarDatosFragment;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirmardatos_solicitarmedico.ConfirmarDatosViewModel;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarPrestadorFragment extends Fragment {

    private List<String> prestadoresList = new ArrayList<String>();
    private List<String> sintomasPasadosSolicitarMedico = new ArrayList<String>();
    private Spinner prestadoresSpinner;
    private Button btnSiguiente;
    private ListView lvSintomasSeleccionarPrestadorSalud;
    private SeleccionarPrestadorViewModel seleccionarPrestadorViewModel;
    private List<PrestadoraSalud> prestadoraSaludList;
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
        prestadoresList.add("Medica Uruguaya");
        prestadoresList.add("Casmu");
        prestadoresList.add("Asociacion Española");
        prestadoresList.add("Casa de Galicia");
        prestadoresList.add("Hospital Britanico");

        prestadoresSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,prestadoresList));

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
