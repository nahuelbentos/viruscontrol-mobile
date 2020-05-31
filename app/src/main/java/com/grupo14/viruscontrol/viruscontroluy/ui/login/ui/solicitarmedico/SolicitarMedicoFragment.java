package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.solicitarmedico;

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

import java.util.ArrayList;
import java.util.List;

public class SolicitarMedicoFragment extends Fragment {

    Button btnAgregarSintoma;
    Button btnSiguiente;
    EditText editTextSintoma;
    List<String> sintomasList = new ArrayList<String>();
    ListView lvSintomas;

    public static SolicitarMedicoFragment newInstance() {
        return new SolicitarMedicoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View solicitarMedicoView = inflater.inflate(R.layout.fragment_solicitar_medico, container, false);

        editTextSintoma = (EditText) solicitarMedicoView.findViewById(R.id.sintomaEditText);
        btnAgregarSintoma = (Button) solicitarMedicoView.findViewById(R.id.btnAgregarSintoma);
        lvSintomas = (ListView) solicitarMedicoView.findViewById(R.id.lvSintomas);

        btnAgregarSintoma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String stringSintoma = editTextSintoma.getText().toString();
                sintomasList.add(stringSintoma);
                System.out.println("Sintomas: " + sintomasList.toString());
                Toast.makeText(getActivity(),"Sintoma agregado: " + stringSintoma, Toast.LENGTH_SHORT).show();
                editTextSintoma.setText("");
                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,sintomasList);
                lvSintomas.setAdapter(adapter);

            }
        });

        return solicitarMedicoView;

    }

}
