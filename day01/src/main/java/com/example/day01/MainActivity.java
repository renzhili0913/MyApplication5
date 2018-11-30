package com.example.day01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;

public class MainActivity extends AppCompatActivity {
    private RingView ringView;
    private Rview r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ringView=findViewById(R.id.ring);
        r=findViewById(R.id.r);
        ringView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float degrees =  (float)(720 + Math.random() * 1000);
                RotateAnimation rotateAnimation = new RotateAnimation(0,degrees,250,250);
                rotateAnimation.setDuration(5000);
                rotateAnimation.setFillAfter(true);
                r.startAnimation(rotateAnimation);
            }
        });

    }
}
