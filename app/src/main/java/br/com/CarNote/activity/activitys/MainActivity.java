package br.com.CarNote.activity.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import br.com.CarNote.R;

public class MainActivity extends AppCompatActivity {
    private CardView buttonAlcoolOuGasolina;
    private CardView buttonConsumoPorLitro;
    private CardView buttonGastosComCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAlcoolOuGasolina = findViewById(R.id.CardViewAlcoolOuGasolina);
        buttonConsumoPorLitro = findViewById(R.id.CardViewConsumoPorLitro);
        buttonGastosComCarro = findViewById(R.id.CardViewGastosComCarro);

        buttonAlcoolOuGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlcoolOuGasolina.class);
                startActivity(intent);
            }
        });

        buttonConsumoPorLitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsumoPorLitro.class);
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
    }
}