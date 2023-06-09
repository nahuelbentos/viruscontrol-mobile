package com.grupo14.viruscontroluy.receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.grupo14.viruscontroluy.providers.AuthProvider;

public class AcceptReceiver extends BroadcastReceiver {

    private AuthProvider mAuthProvider;

    @Override
    public void onReceive(Context context, Intent intent) {
        String idClient = intent.getExtras().getString("idClient");
        mAuthProvider = new AuthProvider();

//        mGeofireProvider = new GeofireProvider("active_drivers");
//        mGeofireProvider.removeLocation(mAuthProvider.getId());


        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(2);

//        Intent intent1 = new Intent(context, MapDriverBookingActivity.class);
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent1.setAction(Intent.ACTION_RUN);
//        intent1.putExtra("idClient", idClient);
//        context.startActivity(intent1);

    }
}
