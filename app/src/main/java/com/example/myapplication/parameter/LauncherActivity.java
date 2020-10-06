package com.example.myapplication.parameter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class LauncherActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 800;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_launcher);

        new Handler().postDelayed( new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(LauncherActivity.this,MainActivity.class);
                LauncherActivity.this.startActivity(mainIntent);
                LauncherActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}