package com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.cerrarsesion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.FacebookActivity;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.grupo14.viruscontrol.viruscontroluy.R;
import com.grupo14.viruscontrol.viruscontroluy.services.ApiAdapter;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.LoginActivity;
import com.grupo14.viruscontrol.viruscontroluy.ui.login.ui.home.HomeFragment;
import com.grupo14.viruscontrol.viruscontroluy.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CerrarSesionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CerrarSesionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CerrarSesionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CerrarSesionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CerrarSesionFragment newInstance(String param1, String param2) {
        CerrarSesionFragment fragment = new CerrarSesionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

        LoginManager.getInstance().logOut();
        Intent i = new Intent(getActivity(), LoginActivity.class);
        String accessToken = Utility.getInstance().getSessionToken();
        Log.v("LogoutBackend ::: accessToken", "Code " + Utility.getInstance().getSessionToken());

        Call<String> logoutBackend = ApiAdapter.getApiService().logoutBackend(accessToken);
        logoutBackend.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.v("LogoutBackend ::: response", "Code " + response.code());
                }
                Toast.makeText(getActivity(), "Se cierra sesion " , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //Toast.makeText(getActivity(), "LogoutBackend - Fail ::: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v("LogoutBackend ::: Fail", "Code " + t.getMessage());
            }
        });

        startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
    }
}