package com.example.snapwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {
    private TextView timeView;
    private int seconds;
    private int hours, minute, secs;
    private boolean running, wasRunning;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        getSupportActionBar().hide();

        if (savedInstanceState != null) {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    public void onStart(View view) {
        running = true;
    }
    public void onStop(View view){
        running = false;
    }
    public void onReset(View view){
        running = false;
        seconds = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();

        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    private void runTimer() {
        timeView = findViewById(R.id.timeView);
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    seconds++;
                }

                hours = seconds / 3600;
                minute = (seconds / 3600) / 60;
                secs = seconds % 60;

                time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minute, secs);
                timeView.setText(time);

                handler.postDelayed(this, 1000);

            }
        });
    }
}