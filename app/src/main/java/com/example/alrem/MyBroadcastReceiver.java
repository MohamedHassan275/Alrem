package com.example.alrem;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.alrem.Activites.SplashActivity;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mp;

            mp=MediaPlayer.create(context, R.raw.song2);
            mp.start();

             Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

             PendingIntent  notifction=PendingIntent.getActivity(context,0,new Intent
                     (context, SplashActivity.class),0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle("j.dsd1  1")
                .setContentText("aali");
        builder.setContentIntent(notifction);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setAutoCancel(true);
        NotificationManager mm= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mm.cancel(1);
        mm.notify(1,builder.build());

        }
    };


