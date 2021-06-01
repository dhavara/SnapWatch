package com.example.snapwatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;
    //variables
    Animation topAnim, botAnim;
    ImageView image;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // utk menghilangkan bar diatas
        setContentView(R.layout.activity_splash);

        //Animation
        topAnim = AnimationUtils.loadAnimation (this,R.anim.top_anim);
        botAnim = AnimationUtils.loadAnimation (this,R.anim.buttom_anim);


        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);


        image.setAnimation(topAnim);
        logo.setAnimation(botAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);



    }
}