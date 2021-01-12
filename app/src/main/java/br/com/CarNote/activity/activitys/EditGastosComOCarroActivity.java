package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import br.com.CarNote.R;
import br.com.CarNote.activity.DataBase.GastosDAO;
import br.com.CarNote.activity.model.Gastos;

public class EditGastosComOCarroActivity extends AppCompatActivity {
    private TextInputEditText textTitulo;
    private EditText textPreco;
    private EditText textDetalhes;
    private Button buttonSalvar;
    private Boolean camposValidos = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gastos_com_o_carro);
        textTitulo = findViewById(R.id.TextInputEditTextGastos);
        textPreco = findViewById(R.id.editTextNumberDecimalPreco);
        textDetalhes = findViewById(R.id.editTextTextMultiLineDetalhes);
        buttonSalvar = findViewById(R.id.buttonSalvarNovoGasto);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dadosValidos();

            }
        });
    }
    public void dadosValidos(){

            if(textTitulo.equals("") || textTitulo.equals(null)){
                textTitulo.setError("!");
               camposValidos = false;
            }else if(textPreco.equals("") || textPreco.equals(null)){
                textPreco.setError("!");
                camposValidos = false;

            }else {
               camposValidos = true;
               salvar();
            }

    }
    public void salvar(){
        double preco = Double.parseDouble(textPreco.getText().toString());
        Gastos gastos = new Gastos(textTitulo.getText().toString(),
               preco ,textDetalhes.getText().toString());
        GastosDAO gastosDAO = new GastosDAO(getApplicationContext());
        gastosDAO.salvar(gastos);
    }
}