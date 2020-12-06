package br.com.CarNote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import br.com.CarNote.R;

public class MainActivity extends AppCompatActivity {
    private ImageView buttonAlcoolOuGasolina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAlcoolOuGasolina = findViewById(R.id.buttonAlcoolOuGasolina);

        buttonAlcoolOuGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AlcoolOuGasolina.class);
                startActivity(intent);
            }
        });

    }

}