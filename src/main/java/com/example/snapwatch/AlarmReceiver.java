package com.example.snapwatch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer player;
    @Override
    public void onReceive(Context c, Intent intent) {
        Toast.makeText(c, "Alarm Berbunyi", Toast.LENGTH_LONG).show();
        player = MediaPlayer.create(c, R.raw.tone);
        player.start();
    }
}
