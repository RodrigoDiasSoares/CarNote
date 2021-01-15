package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.CarNote.R;

public class EditGastosComOCarroActivity extends AppCompatActivity {
    private TextInputEditText editTextGasto;
    private TextInputEditText editTextPreco;
    private TextInputLayout textInputLayoutGasto;
    private TextInputLayout textInputLayoutPreco;
    private EditText editTextDetalhes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gastos_com_o_carro);
        editTextGasto = findViewById(R.id.textInputEditTextPreco);
        editTextPreco = findViewById(R.id.textInputEditTextPreco);
        textInputLayoutGasto = findViewById(R.id.textInputLayoutGastos);
        textInputLayoutPreco = findViewById(R.id.textInputLayoutPreco);
        editTextDetalhes = findViewById(R.id.editTextTextDetalhes);
    }
}