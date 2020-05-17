package com.example.ezstopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvTimer;
    Button btnStart;
    Button btnPause;
    Button btnReset;

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
                Toast.makeText(MainActivity.this, "_START_", Toast.LENGTH_SHORT).show();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                Toast.makeText(MainActivity.this, "_PAUSE_", Toast.LENGTH_SHORT).show();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
                Toast.makeText(MainActivity.this, "_RESET_", Toast.LENGTH_SHORT).show();
            }
        });
        //we check if the bundle saveInstantState is not null
        //then we return the previous values of the seconds and running
        //to the activity
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();

    }

    //We use this method because when we rotated the device
    //we noticed that the activity resets itself and then restarts
    //because of this our progress is lost
    //so we save the values using this function
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }


//    public void wait1sec() {
//        long startTime = System.currentTimeMillis();
//        while (System.currentTimeMillis() < startTime + 1000) ;
//    }

    public void runTimer() {


        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = (seconds % 60);

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                tvTimer.setText(time);

                if (running) {
//                    wait1sec();
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });


    }
}
