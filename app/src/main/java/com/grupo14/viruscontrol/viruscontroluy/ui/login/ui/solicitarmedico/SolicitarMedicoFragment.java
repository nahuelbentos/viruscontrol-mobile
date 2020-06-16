package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.solicitarmedico;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.grupo14.viruscontrol.viruscontroluy.R;
import com.grupo14.viruscontrol.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontrol.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirmardatos_solicitarmedico.ConfirmarDatosFragment;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.seleccionarprestador.SeleccionarPrestadorFragment;
import com.grupo14.viruscontrol.viruscontroluy.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitarMedicoFragment extends Fragment {

    private SolicitarMedicoViewModel mViewModel;

    private Button btnAgregarSintoma;
    private Button btnSiguiente;
    //private EditText editTextSintoma;

    private List<String> sintomasIngresadosSolicitarMedico = new ArrayList<String>();
    private ListView lvSintomasIngresadosSolicitarMedico;
    private Spinner spinnerSintomasSolicitarMedico;
    private List<String> sintomasListFromBackend = new ArrayList<>();

    private SolicitarMedicoViewModel solicitarMedicoViewModel;

    public static SolicitarMedicoFragment newInstance() {
        return new SolicitarMedicoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



//        try
//        {
//            Response<List<Sintoma>> response = sintomasCall.execute();
//            List<Sintoma> sintomasList = response.body();
//            for (Sintoma sintoma : sintomasList) {
//                Log.v("sintoma",sintoma.getNombre());
//                sintomasMap.put(sintoma.getNombre(),sintoma.getId());
//            }
//            //API response
//            System.out.println(sintomasList);
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }





        solicitarMedicoViewModel =
                ViewModelProviders.of(this).get(SolicitarMedicoViewModel.class);

        //TODO: Cargar sintomasListFromBackend desde el backend
        //mienstras se cargar manualmente
        List<Sintoma> lista = Utility.getInstance().getSintomaList();
        for(Sintoma sintoma : lista){
            sintomasListFromBackend.add(sintoma.getNombre());
        }


        final View solicitarMedicoView = inflater.inflate(R.layout.fragment_solicitar_medico, container, false);
        solicitarMedicoViewModel.getSintomaList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, strings);
                lvSintomasIngresadosSolicitarMedico.setAdapter(adapter);
            }
        });

        //editTextSintoma = (EditText) solicitarMedicoView.findViewById(R.id.sintomaEditText);
        btnAgregarSintoma = (Button) solicitarMedicoView.findViewById(R.id.btnAgregarSintoma);
        lvSintomasIngresadosSolicitarMedico = (ListView) solicitarMedicoView.findViewById(R.id.lvSintomasIngresadosSolicitarMedico);
        btnSiguiente = (Button) solicitarMedicoView.findViewById(R.id.btnSiguienteSolicitarMedico);
        spinnerSintomasSolicitarMedico = (Spinner) solicitarMedicoView.findViewById(R.id.spinner_seleccionar_sintomas);
        spinnerSintomasSolicitarMedico.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,sintomasListFromBackend));
        btnAgregarSintoma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                    try {
                        String sintomaSeleccionado = spinnerSintomasSolicitarMedico.getSelectedItem().toString();
                        sintomasIngresadosSolicitarMedico.add(sintomaSeleccionado);

                        int posSelectedItem = spinnerSintomasSolicitarMedico.getSelectedItemPosition();
                        sintomasListFromBackend.remove(posSelectedItem);

                        spinnerSintomasSolicitarMedico.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,sintomasListFromBackend));
                        lvSintomasIngresadosSolicitarMedico.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,sintomasIngresadosSolicitarMedico));
                    }catch (Exception e){
                        Log.v("Error ::: SolicitarMedico: No hay mas sintomas para agregar", e.getMessage());
                        Toast.makeText(getActivity(), "No hay mas sintomas para agregar!", Toast.LENGTH_LONG).show();
                    }
            }
        });

        btnSiguiente = (Button) solicitarMedicoView.findViewById(R.id.btnSiguienteSolicitarMedico);
        btnSiguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!sintomasIngresadosSolicitarMedico.isEmpty()){
                    Bundle datosAEnviar = new Bundle();
                    datosAEnviar.putStringArrayList("sintomasList", (ArrayList<String>) sintomasIngresadosSolicitarMedico);


                    Fragment nuevoFragmento = ConfirmarDatosFragment.newInstance();
                    nuevoFragmento.setArguments(datosAEnviar);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                    transaction.addToBackStack(null);

                    // Commit a la transacción
                    transaction.commit();
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar al menos un sintoma", Toast.LENGTH_LONG).show();
                }

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
