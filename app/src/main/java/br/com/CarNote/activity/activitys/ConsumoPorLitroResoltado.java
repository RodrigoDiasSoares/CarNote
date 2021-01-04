package br.com.CarNote.activity.activitys;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.CarNote.R;

public class ConsumoPorLitroResoltado extends AppCompatActivity {
    private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_ou_gasolina_resoltado);
        resultado = findViewById(R.id.textViewResultado2);

        Bundle bundle = getIntent().getExtras();

        resultado.setText(String.valueOf(bundle.getDouble("resultado")));
    }
}