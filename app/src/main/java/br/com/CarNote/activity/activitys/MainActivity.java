package br.com.CarNote.activity.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import br.com.CarNote.R;

public class MainActivity extends AppCompatActivity {
    private ImageView buttonAlcoolOuGasolina;
    private ImageView buttonConsumoPorLitro;
    private ImageView buttonGastosComCarro;
    private ImageView buttonTutorial;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAlcoolOuGasolina = findViewById(R.id.btnAlcoolOuGasolina);
        buttonConsumoPorLitro = findViewById(R.id.btnConsumoPorLitro);
        buttonGastosComCarro = findViewById(R.id.btnGastosComCarro);
        buttonTutorial = findViewById(R.id.btnTutorial);
        mAdView = findViewById(R.id.adViewMain);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                carregarBanner();
            }
        }, 1000);

        buttonAlcoolOuGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlcoolOuGasolinaActivity.class);
                startActivity(intent);
                overridePendingTransition(0, android.R.anim.fade_out);
            }
        });


        buttonConsumoPorLitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConsumoPorLitroActivity.class);
                startActivity(intent);
            }
        });

        buttonGastosComCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GastoComOCarroActivity.class);
                startActivity(intent);
            }
        });

        buttonTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TutorialActivity.class);
                startActivity(intent);
            }
        });
   }
    public void carregarBanner(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}