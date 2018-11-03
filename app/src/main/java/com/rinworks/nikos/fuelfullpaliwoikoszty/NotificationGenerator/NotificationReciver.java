package com.rinworks.nikos.fuelfullpaliwoikoszty.NotificationGenerator;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.rinworks.nikos.fuelfullpaliwoikoszty.R;

import java.util.Calendar;

public class NotificationReciver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        //test
        Bundle extra = intent.getExtras();
        String tytul = extra.getString("Tytuł");
        String tresc = extra.getString("Treść");

        showNotification(context,tytul,tresc,intent);


//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//            int notificationId = (int) com.rinworks.nikos.fuelfullpaliwoikoszty.Database
//                    .SharedPreferences.getFloat("NotiID");
//            com.rinworks.nikos.fuelfullpaliwoikoszty.Database.SharedPreferences.setFloat
//                    ("NotiID",notificationId+1);
//            String channelId = "channel-01";
//            String channelName = "Channel Name";
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                NotificationChannel mChannel = new NotificationChannel(
//                        channelId, channelName, importance);
//                notificationManager.createNotificationChannel(mChannel);
//            }
//
//            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle(tytul)
//                    .setColorized(true)
//                    .setContentText(tresc)
//                    .setAutoCancel(true)
//                    .setColor(0xfff68a4c)
//                    .setSound(defaultSoundUri)
//                    .setVibrate(new long[]{1000,1000,1000,1000});
//
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//            stackBuilder.addNextIntent(intent);
//            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
//                    0,
//                    PendingIntent.FLAG_UPDATE_CURRENT
//            );
//
//            mBuilder.setContentIntent(resultPendingIntent);
//
//            notificationManager.notify(notificationId, mBuilder.build());
}

    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = (int) System.currentTimeMillis();

        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setColorized(true)
                .setContentText(body)
                .setAutoCancel(true)
                .setColor(0xfff68a4c)
                .setSound(defaultSoundUri)
                .setLights(0xfff68a4c,3000,3000)
                .setVibrate(new long[]{0,1000,500,1000});

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_ONE_SHOT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
        Log.d("INT",  ""+notificationId);
    }
    }


