package com.grupo14.viruscontroluy.receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class CancelReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String idClient = intent.getExtras().getString("idClient");
    //    mClientBookingProvider = new ClientBookingProvider();
    //    mClientBookingProvider.updateStatus(idClient, "cancelled");

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(2);

    }
}
