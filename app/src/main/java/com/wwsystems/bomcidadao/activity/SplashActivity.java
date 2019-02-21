package com.wwsystems.bomcidadao.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.wwsystems.bomcidadao.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    private ImageView logoWWSystems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

        logoWWSystems = findViewById(R.id.logoWWSystems);
        logoWWSystems.setAnimation(AnimationUtils.loadAnimation(this, R.anim.heart_pulse));

    }
}
