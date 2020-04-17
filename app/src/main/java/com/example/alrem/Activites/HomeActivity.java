package com.example.alrem.Activites;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.alrem.R;
import com.example.alrem.Services.HelloServic;

import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    private final static String TAG = "HomeActivity";
    private static final long START_TIME_IN_MILLIS = 60000;

    private TextView mtextViewCondition;
    private Button mbouttonStartRest, mboutonRest;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunnig;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mtextViewCondition = findViewById(R.id.text_view_condition);
        mbouttonStartRest = findViewById(R.id.boutton_start_rest);
        mboutonRest = findViewById(R.id.bouton_rest);


        startService(new Intent(this, HelloServic.class));
        Log.i(TAG, "Started service");

        mbouttonStartRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTimerRunnig) {
                    pauseTimer();
                } else {
                   // startTimer();
                }

            }
        });
        mboutonRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }


    private void StartService() {
        startService(new Intent(getBaseContext(), HelloServic.class));


        Intent mIntent = new Intent(HomeActivity.this, HelloServic.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(HomeActivity.this,
                0, mIntent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 12);
        calendar.set(Calendar.SECOND, 00);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

   /* private HelloServic helloServic = new HelloServic() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent); // or whatever method used to update your GUI fields
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(helloServic, new IntentFilter(HelloServic.COUNTDOWN_BR));
        Log.i(TAG, "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(helloServic);
        Log.i(TAG, "Unregistered broacast receiver");
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(helloServic);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }*/

    @Override
    public void onDestroy() {
        stopService(new Intent(this, HelloServic.class));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
        }

    }

    private void pauseTimer() {

        mCountDownTimer.cancel();
        mTimerRunnig = false;
        mbouttonStartRest.setText("start");
        mboutonRest.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {

        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mboutonRest.setVisibility(View.INVISIBLE);

        mbouttonStartRest.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutse = (int) (mTimeLeftInMillis / 1000) / 60;
        int second = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutse, second);
        mtextViewCondition.setText(timeLeftFormatted);
    }

    //////
    public void startService(View view) {
        startService(new Intent(getBaseContext(), HelloServic.class));


        Intent mIntent = new Intent(HomeActivity.this, HelloServic.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(HomeActivity.this,
                0, mIntent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 12);
        calendar.set(Calendar.SECOND, 00);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void stopService(View view) {
        startService(new Intent(getBaseContext(), HelloServic.class));
    }

    public void startrest(View view) {
        resetTimer();
        updateCountDownText();

    }

    public void start(View view) {
    }


//    public void start(View view) {
//        if (mTimerRunnig) {
//            pauseTimer();
//        } else {
//            startTimer();
//        }
//    }


//

}

//  public void startService1(View view) {
//        // startService(new Intent(getBaseContext(),HelloServic.class));
//        showDialog(1);
//
//    }
//
//
//  protected Dialog onCreateDialog(int id){
//      Calendar c = Calendar.getInstance();
//      if (id == 1)
//          return new TimePickerDialog(Main2Activity.this, Timap, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
//      return null;
//   }
//
//    protected TimePickerDialog.OnTimeSetListener Timap = new TimePickerDialog.OnTimeSetListener() {
//        @Override
//        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            calendar.set(Calendar.MINUTE, minute);
//
//            Intent mIntent = new Intent(Main2Activity.this, HelloServic.class);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            PendingIntent pendingIntent = PendingIntent.getService(Main2Activity.this,
//                    0, mIntent, 0);
//
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                    AlarmManager.INTERVAL_DAY, pendingIntent);
//
//
//time.setText(hourOfDay+""+minute);
//        }
//    };


//////////////////////

//    public void startService1(View view) {
//        // startService(new Intent(getBaseContext(),HelloServic.class));
//        showDialog(1);
//
//    }


//    Calendar c = Calendar.getInstance();
//    int m = c.get(Calendar.MINUTE);
//    long start = System.currentTimeMillis();
//    int remain=0;
//        if (m<1)
//        {
//        remain = 1-m;
//        }
//        else if (m<30)
//        {
//        remain = 30-m;
//        }
//        else if (m<45)
//        {
//        remain = 45-m;
//        }
//        else
//        {
//        remain = 60-m;
//        }
//        remain= (int) (start + remain *15*60*1000);

// AlarmManager am=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
////        Intent intent = new Intent(this, HelloServic.class);
////        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
////        am.set(AlarmManager.RTC_WAKEUP,remain, pi);


//    private void startTimer() {
//
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//
//            @Override
//            public void onFinish() {
//                mTimerRunnig = false;
//                mbouttonStartRest.setText("Start");
//                mbouttonStartRest.setVisibility(View.INVISIBLE);
//                mboutonRest.setVisibility(View.VISIBLE);
//                //servic
//                StartService();
//
//
//                Toast.makeText(HomeActivity.this, "mediaplayer", Toast.LENGTH_SHORT).show();
//
//                // startTimer();
//
//                //function
//                updateCountDownText();
//
//
//            }
//        }.start();
//        mTimerRunnig = true;
//        mbouttonStartRest.setText("pause");
//        mboutonRest.setVisibility(View.INVISIBLE);
//
//    }