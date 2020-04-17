package com.example.alrem.Services;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import android.os.Build;
import android.os.IBinder;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;



import static com.example.alrem.R.raw.song2;


public class HelloServic extends Service {
    MediaPlayer mediaPlayer;
    public void onCreate() {
        Toast.makeText(this, "Service is created", Toast.LENGTH_SHORT).show();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Alert", Toast.LENGTH_SHORT).show();
        if(mediaPlayer==null) {
            mediaPlayer = MediaPlayer.create(this, song2);
        }        mediaPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destory", Toast.LENGTH_SHORT).show();




    }
}
