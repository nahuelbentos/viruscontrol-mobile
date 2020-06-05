package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.confirardatos_solicitarmedico;

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
import android.widget.Toast;

import com.grupo14.viruscontrol.viruscontroluy.R;
import com.grupo14.viruscontrol.viruscontroluy.modelos.Medico;
import com.grupo14.viruscontrol.viruscontroluy.modelos.PrestadoraSalud;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.gallery.GalleryFragment;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.home.HomeFragment;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.seleccionarprestador.SeleccionarPrestadorFragment;

import java.util.ArrayList;
import java.util.List;

public class ConfirmarDatosFragment extends Fragment {

    private ConfirmarDatosViewModel mViewModel;
    private String prestadoraSalud;
    private List<String> sintomasList;
    private Medico medicoAsignado;
    private ListView lvSintomasListConfirmarDatos;
    private Button confirmarVisitaSolicitarMedico;

    //Atributos temporales para prueba
    private String correoMedico = "dr.house@netflix.com";
    private String nombreMedico = "Gregory";
    private String apellidoMedico = "House";

    public static ConfirmarDatosFragment newInstance() {
        return new ConfirmarDatosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View confirmarDatosView;
        confirmarDatosView = inflater.inflate(R.layout.fragment_confirmar_datos, container, false);

        mViewModel = new ConfirmarDatosViewModel();

        //TODO: obtener medico asignado mediante servicio REST

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null) {
            sintomasList = datosRecuperados.getStringArrayList("sintomasList");
            prestadoraSalud = datosRecuperados.getString("prestadoraSalud");
            mViewModel.setSintomasList(sintomasList);
            //TODO: Obtener prestadora mediante su clave, ver como
            mViewModel.setPrestadoraSalud(new PrestadoraSalud());

            lvSintomasListConfirmarDatos = confirmarDatosView.findViewById(R.id.sintomasIngresadosConfirmarDatos);
            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mViewModel.getSintomasList().getValue());
            lvSintomasListConfirmarDatos.setAdapter(adapter);
        }

        confirmarVisitaSolicitarMedico = confirmarDatosView.findViewById(R.id.btnConfirmarVisita);
        confirmarVisitaSolicitarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Hacer POST para enviar los datos
                Toast.makeText(getActivity(),"Visita confimada!", Toast.LENGTH_SHORT).show();
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
