package br.com.CarNote.activity.activitys;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import br.com.CarNote.R;

public class ConsumoPorLitroResoltado extends AppCompatActivity {
    private TextView resultado;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo_por_litro_resoltado);
        resultado = findViewById(R.id.textViewResultado2);

        Bundle bundle = getIntent().getExtras();

        resultado.setText(String.valueOf(decimalFormat.format(bundle.getDouble("resultado")))
                            +" Km \npor litro");
    }
}