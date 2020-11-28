package br.com.possante_alcoolougasolina.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.com.possante_alcoolougasolina.R;

public class AlcoolOuGasolinaResoltado extends AppCompatActivity {
    private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_ou_gasolina_resoltado);
        resultado = findViewById(R.id.textViewResultado2);

        Bundle bundle = getIntent().getExtras();
        String valor = bundle.getString("combustivel");
        resultado.setText(valor);
    }
}