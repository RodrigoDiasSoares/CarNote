package br.com.CarNote.activity.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.CarNote.R;

public class MainActivity extends AppCompatActivity {
    private ImageView buttonAlcoolOuGasolina;
    private ImageView buttonConsumoPorLitro;
    private ImageView buttonGastosComCarro;
    private ImageView buttonTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAlcoolOuGasolina = findViewById(R.id.btnAlcoolOuGasolina);
        buttonConsumoPorLitro = findViewById(R.id.btnConsumoPorLitro);
        buttonGastosComCarro = findViewById(R.id.btnGastosComCarro);
        buttonTutorial = findViewById(R.id.btnTutorial);

        buttonAlcoolOuGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlcoolOuGasolinaActivity.class);
                startActivity(intent);
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
}