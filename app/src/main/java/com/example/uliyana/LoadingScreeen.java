package com.example.uliyana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import io.realm.Realm;

public class LoadingScreeen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screeen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreeen.this, MainActivity.class));
                finish();
            }
        },3*1000);
    }
}