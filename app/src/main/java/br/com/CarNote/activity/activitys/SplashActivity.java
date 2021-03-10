package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.CarNote.R;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sPreferences = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sPreferences = getSharedPreferences("firstRun", MODE_PRIVATE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sPreferences.getBoolean("firstRun", true)) {
                    sPreferences.edit().putBoolean("firstRun", false).apply();
                    startActivity(new Intent(getApplicationContext(), TutorialActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }
        }, 3000);


    }
}