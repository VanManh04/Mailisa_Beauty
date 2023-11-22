package com.example.mailisa_beauty;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mailisa_beauty.Login.dang_Nhap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        View welcomeText = findViewById(R.id.startsrc);
        welcomeText.startAnimation(fadeIn);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, dang_Nhap.class);
                startActivity(intent);
            }
        },3000);
    }
}