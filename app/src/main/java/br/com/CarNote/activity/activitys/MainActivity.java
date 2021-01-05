package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import br.com.CarNote.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonAlcoolOuGasolina;
    private Button buttonConsumoPorLitro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAlcoolOuGasolina = findViewById(R.id.buttonAlcoolOuGasolina);
        buttonConsumoPorLitro = findViewById(R.id.buttonConsumoPorLitro);

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

    }

}