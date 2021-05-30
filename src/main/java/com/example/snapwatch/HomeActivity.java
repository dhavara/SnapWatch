package com.example.snapwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button button_stopwatch, button_alarm, button_memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        StopwatchListener();
        AlarmListener();
        MemoListener();
    }

    private void MemoListener() {
        button_memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MemoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AlarmListener() {
        button_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AlarmActivity.class);
                startActivity(intent);
            }
        });
    }

    private void StopwatchListener() {
        button_stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), StopwatchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        button_stopwatch = findViewById(R.id.button_stopwatch);
        button_alarm = findViewById(R.id.button_alarm);
        button_memo = findViewById(R.id.button_memo);
    }
}