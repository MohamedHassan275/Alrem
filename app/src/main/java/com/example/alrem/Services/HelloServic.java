package com.example.alrem.Services;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;



import static com.example.alrem.R.raw.song2;


public class HelloServic extends Service {

    private final static String TAG = "HelloServic";

    public static final String COUNTDOWN_BR = "com.example.alrem.Services";
    Intent bi = new Intent(COUNTDOWN_BR);

    CountDownTimer cdt = null;

        MediaPlayer mediaPlayer;


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
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting timer...");

        cdt = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
                bi.putExtra("countdown", millisUntilFinished);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
            }
        };

        cdt.start();
    }

    @Override
    public void onDestroy() {

        cdt.cancel();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }

    }
