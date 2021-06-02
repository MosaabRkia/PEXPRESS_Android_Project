package com.example.pexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int TIMER1 = 1500;
    private static int TIMER = 3000;
     ImageView X;
     TextView Text1;
     TextView Text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Text1 = findViewById(R.id.SplashScreenText);
        Text2 = findViewById(R.id.SplashScreenText1);
        X = findViewById(R.id.SplashScreenLogo);
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(1000);
        anim.setRepeatCount(2);
        anim.setRepeatMode(Animation.REVERSE);



        X.animate().rotationXBy(-360f);
        X.startAnimation(anim);
        Text1.startAnimation(anim);
        Text2.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                X.animate().rotationXBy(180);
                X.animate().rotationYBy(180);
                X.animate().translationX(10);
            }

        },TIMER1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                X.setVisibility(View.VISIBLE);
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        },TIMER);



    }

}