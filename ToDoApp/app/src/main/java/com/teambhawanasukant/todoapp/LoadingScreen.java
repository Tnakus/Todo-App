package com.teambhawanasukant.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        getSupportActionBar().hide();

        //This is what to display after
        final Intent i=new Intent(LoadingScreen.this, Login.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                 startActivity(i);
                 finish();
            }
        },1500);
    }
}
