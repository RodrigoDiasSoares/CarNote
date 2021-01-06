package br.com.CarNote.activity.activitys;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.DecimalFormat;

import br.com.CarNote.R;

public class ConsumoPorLitroResoltado extends AppCompatActivity {
    private TextView resultado;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo_por_litro_resoltado);
        resultado = findViewById(R.id.textViewResultado2);
        mAdView = findViewById(R.id.adViewConsumoPorLitroResultado);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Bundle bundle = getIntent().getExtras();

        resultado.setText(String.valueOf(decimalFormat.format(bundle.getDouble("resultado")))
                            +" Km \npor litro");
    }
}