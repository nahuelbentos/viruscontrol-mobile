package com.grupo14.viruscontroluy.providers;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.grupo14.viruscontroluy.modelos.Token;
import com.grupo14.viruscontroluy.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenProvider {

    DatabaseReference mDatabase;
    private LoginBackendProvider mLoginBackendProvider;

    public TokenProvider(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference().child("Tokens");
        this.mLoginBackendProvider = new LoginBackendProvider(context);
    }

    public void create(String idUser){
        if(idUser == null){
            return;
        }
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                Token token = new Token(instanceIdResult.getToken());
                mDatabase.child(idUser).setValue(token);
                Utility.getInstance().setPushToken(token);

                Log.d("setTokenPush", "onResponse: seteo el token correctamente: token "+ token.getToken());
                Log.d("setTokenPush", "onResponse: seteo el token correctamente: getSessionToken "+ Utility.getInstance().getSessionToken());
                mLoginBackendProvider.setPushToken(Utility.getInstance().getSessionToken(), token.getToken()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Log.d("setTokenPush", "onResponse: seteo el token correctamente ");
                        }else{
                            Log.d("setTokenPush", "onResponse: seteo el token correctamente ");

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("setTokenPush", "onFailure: seteo el token:  " + t.getMessage() );

                    }
                });

            }
        });
    }

    public DatabaseReference getToken(String idUser){
        return mDatabase.child(idUser);
    }
}
