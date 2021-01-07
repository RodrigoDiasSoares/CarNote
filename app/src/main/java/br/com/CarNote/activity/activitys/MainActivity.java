package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import br.com.CarNote.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonAlcoolOuGasolina;
    private Button buttonConsumoPorLitro;
    private Button buttonGastosComOCaarro;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAlcoolOuGasolina = findViewById(R.id.buttonAlcoolOuGasolina);
        buttonConsumoPorLitro = findViewById(R.id.buttonConsumoPorLitro);
        buttonGastosComOCaarro = findViewById(R.id.buttonGastosComOCarro);
        mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        buttonAlcoolOuGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AlcoolOuGasolina.class);
                startActivity(intent);
            }
        });

        buttonConsumoPorLitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ConsumoPorLitro.class);
                startActivity(intent);
            }
        });
        buttonGastosComOCaarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GastoComOCarroActivity.class);
                startActivity(intent);
            }
        });

    }

}