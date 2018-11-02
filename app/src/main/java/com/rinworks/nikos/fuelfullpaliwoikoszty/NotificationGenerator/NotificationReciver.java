package com.rinworks.nikos.fuelfullpaliwoikoszty.NotificationGenerator;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context
                .NOTIFICATION_SERVICE);

        Intent clickedIntent = new Intent(context,clickedActivity.class);
        clickedIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //test
        Bundle extra = intent.getExtras();
        String tytul = extra.getString("Tytuł");
        String tresc = extra.getString("Treść");


        PendingIntent pendingIntent = PendingIntent.getActivity(context,687,clickedIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle(tytul.toUpperCase())
                .setContentText(tresc)
                .setAutoCancel(true);

        notificationManager.notify(687,builder.build());
    }
}
