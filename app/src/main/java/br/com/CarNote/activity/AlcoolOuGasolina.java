package br.com.CarNote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.CarNote.R;

public class AlcoolOuGasolina extends AppCompatActivity {
    private TextInputEditText editTextGasolina;
    private TextInputEditText editTextAlcool;
    private TextInputLayout textInputLayoutGasolina;
    private TextInputLayout textInputLayoutAcool;

    private Button buttonCalcular;
    private TextView textViewResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_ou_gasolina);
        editTextGasolina = findViewById(R.id.TextInputEditTextGasolina);
        editTextAlcool = findViewById(R.id.TextInputEditTextAlcool);
        textInputLayoutAcool = findViewById(R.id.textInputLayoutAlcool);
        textInputLayoutGasolina = findViewById(R.id.textInputLayoutGasolina);
        buttonCalcular = findViewById(R.id.buttonCalcular);


        editTextAlcool.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    editTextGasolina.requestFocus();
                    return true;
                }
                return false;
            }
        });
        editTextGasolina.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    calcularPreco(v);
                    return true;
                }
                return false;
            }
        });

    }

    public void calcularPreco(View view){
        String precoAlcool = editTextAlcool.getText().toString();
        String precoGasolina = editTextGasolina.getText().toString();

        String camposValidos = validarCampos(precoAlcool, precoGasolina);
        if(camposValidos.equals("true")){
            try {
                textInputLayoutGasolina.setErrorEnabled(false);
                textInputLayoutAcool.setErrorEnabled(false);
                equação(precoAlcool, precoGasolina);
            }catch (NumberFormatException e){
                precoAlcool = precoAlcool.replace(',','.');
                precoGasolina = precoGasolina.replace(',' , '.');
                equação(precoAlcool, precoGasolina);
            }
        }else {
            validarCampos(precoAlcool, precoGasolina);
        }
    }
    public void equação(String precoAlcool, String precoGasolina){
        double valorAlcool = Double.parseDouble(precoAlcool);
        double valorGasolina = Double.parseDouble(precoGasolina);
        double valorBase = valorGasolina * 0.7;


        if(valorAlcool <= valorBase){

            Intent intent = new Intent(getBaseContext(), AlcoolOuGasolinaResoltado.class);
            intent.putExtra("combustivel","Álcool");
            startActivity(intent);
        }else{

            Intent intent = new Intent(getBaseContext(),AlcoolOuGasolinaResoltado.class);
            intent.putExtra("combustivel","Gasolina");
            startActivity(intent);
        }
    }


    public String validarCampos(String precoAlcool, String precoGasolina){
        String camposValidos = "true";
        if(precoAlcool.equals("") && precoGasolina.equals("")){
            camposValidos = "Preencha os campos!!";
        }else
        if(precoAlcool == null && precoGasolina == null){
            camposValidos = "Preencha os campos!!";
        }else
        if(precoAlcool == null || precoAlcool.equals("")){
            camposValidos = "Álcool";
        }else
        if(precoGasolina == null || precoGasolina.equals("")){
            camposValidos = "Gasolina";
        }


        if(!camposValidos.equals("true")){
            if(camposValidos.equals("Álcool")){
                textInputLayoutAcool.setError("!");
                textInputLayoutGasolina.setErrorEnabled(false);

            }else if(camposValidos.equals("Gasolina")){
                textInputLayoutGasolina.setError("!");
                textInputLayoutAcool.setErrorEnabled(false);

            }else {
                textInputLayoutAcool.setError("!");
                textInputLayoutGasolina.setError("!");
            }

        }

        return camposValidos;
    }

}