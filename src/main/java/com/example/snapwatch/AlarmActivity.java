package com.example.snapwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.os.Build.VERSION_CODES.O;
import static java.util.Calendar.HOUR_OF_DAY;

public class AlarmActivity extends AppCompatActivity {
TimePickerDialog timePickerDialog;
Button SetAlarm;
TextView textAlarm;
TimePicker myTimePicker;

final static int RQS_1=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        textAlarm = findViewById(R.id.txt_alarm);
        SetAlarm = findViewById(R.id.btn_SetAlarm);

        SetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAlarm.setText("");
                openTimePickerDialog(false);
            }
        });
    }

    private void openTimePickerDialog(boolean is24r){
        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(AlarmActivity.this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),true);
        timePickerDialog.setTitle("set waktu alarm");
        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();
            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND,0);

            if(calSet.compareTo(calNow)<=0){
                calSet.add(Calendar.DATE, 1);
            }
            SetAlarm(calSet);
        }
    };
    private void SetAlarm(Calendar targetCal){
        textAlarm.setText("Alarm Set for "+ targetCal.getTime());
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),RQS_1,intent,0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,targetCal.getTimeInMillis(),pendingIntent);
    }
}