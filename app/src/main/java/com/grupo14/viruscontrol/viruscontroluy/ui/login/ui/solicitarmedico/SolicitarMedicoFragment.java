package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.solicitarmedico;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.grupo14.viruscontrol.viruscontroluy.R;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.seleccionarprestador.SeleccionarPrestadorFragment;

import java.util.ArrayList;
import java.util.List;

public class SolicitarMedicoFragment extends Fragment {

    private SolicitarMedicoViewModel mViewModel;

    private Button btnAgregarSintoma;
    private Button btnSiguiente;
    private EditText editTextSintoma;
    private List<String> sintomasList = new ArrayList<String>();
    private ListView lvSintomas;

    private SolicitarMedicoViewModel solicitarMedicoViewModel;

    public static SolicitarMedicoFragment newInstance() {
        return new SolicitarMedicoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        solicitarMedicoViewModel =
                ViewModelProviders.of(this).get(SolicitarMedicoViewModel.class);

        final View solicitarMedicoView = inflater.inflate(R.layout.fragment_solicitar_medico, container, false);
        solicitarMedicoViewModel.getSintomaList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, strings);
                lvSintomas.setAdapter(adapter);
            }
        });

        editTextSintoma = (EditText) solicitarMedicoView.findViewById(R.id.sintomaEditText);
        btnAgregarSintoma = (Button) solicitarMedicoView.findViewById(R.id.btnAgregarSintoma);
        lvSintomas = (ListView) solicitarMedicoView.findViewById(R.id.lvSintomas);
        btnSiguiente = (Button) solicitarMedicoView.findViewById(R.id.btnSintomasSiguiente);

        btnAgregarSintoma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String stringSintoma = editTextSintoma.getText().toString();
                sintomasList.add(stringSintoma);
                solicitarMedicoViewModel.updateSintomas(sintomasList);
                Toast.makeText(getActivity(),"Sintoma agregado: " + stringSintoma, Toast.LENGTH_SHORT).show();
                editTextSintoma.setText("");
                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,sintomasList);
                lvSintomas.setAdapter(adapter);
            }
        });

        btnSiguiente = (Button) solicitarMedicoView.findViewById(R.id.btnSintomasSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Bundle datosAEnviar = new Bundle();
                datosAEnviar.putStringArrayList("sintomasList", (ArrayList<String>) sintomasList);

                Fragment nuevoFragmento = SeleccionarPrestadorFragment.newInstance();
                nuevoFragmento.setArguments(datosAEnviar);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });

        return solicitarMedicoView;
    }
  
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(SolicitarMedicoViewModel.class);
        // TODO: Use the ViewModel

    }

}
