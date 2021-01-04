package br.com.CarNote.activity.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.CarNote.R;

public class ConsumoPorLitro extends AppCompatActivity {
    private TextInputEditText editTextkm;
    private TextInputEditText editTextLitros;
    private TextInputLayout textInputLayoutLitros;
    private TextInputLayout textInputLayoutKm;
    private Button buttonResultadoKmPorLitro;


    private FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo_por_litro);
        editTextkm = findViewById(R.id.TextInputEditTextKm);
        editTextLitros = findViewById(R.id.TextInputEditTextLitros);
        textInputLayoutKm = findViewById(R.id.textInputLayoutKm);
        textInputLayoutLitros = findViewById(R.id.textInputLayoutLitros);
        buttonResultadoKmPorLitro = findViewById(R.id.buttonCalcularConsumo);


        editTextLitros.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    editTextkm.requestFocus();
                    return true;
                }
                return false;
            }
        });
        editTextkm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    calcularKmPorLitro(v);
                    return true;
                }
                return false;
            }
        });
        buttonResultadoKmPorLitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularKmPorLitro(v);
            }
        });


    }

    public void calcularKmPorLitro(View view){
        String litros = editTextLitros.getText().toString();
        String km = editTextkm.getText().toString();

        String camposValidos = validarCampos(litros, km);
        if(camposValidos.equals("true")){
            try {
                textInputLayoutLitros.setErrorEnabled(false);
                textInputLayoutKm.setErrorEnabled(false);
                equação(litros, km);
            }catch (NumberFormatException e){
                litros = litros.replace(',','.');
                km = km.replace(',' , '.');
                equação(litros, km);
            }
        }else {
            validarCampos(litros, km);
        }
    }
    public void equação(String litros, String km){
        double valorlitros = Double.parseDouble(litros);
        double valorKm = Double.parseDouble(km);
        double resultado = valorKm / valorlitros;

        Intent intent = new Intent(getBaseContext(), ConsumoPorLitroResoltado.class);
        intent.putExtra("resultado",resultado);
        startActivity(intent);

    }


    public String validarCampos(String litros, String km){
        String camposValidos = "true";
        if(litros.equals("") && km.equals("")){
            camposValidos = "Preencha os campos!!";
        }else
        if(litros == null && km == null){
            camposValidos = "Preencha os campos!!";
        }else
        if(litros == null || litros.equals("")){
            camposValidos = "Álcool";
        }else
        if(km == null || km.equals("")){
            camposValidos = "Gasolina";
        }


        if(!camposValidos.equals("true")){
            if(camposValidos.equals("Litros")){
                textInputLayoutKm.setError("!");
                textInputLayoutLitros.setErrorEnabled(false);

            }else if(camposValidos.equals("Km")){
                textInputLayoutLitros.setError("!");
                textInputLayoutKm.setErrorEnabled(false);

            }else {
                textInputLayoutKm.setError("!");
                textInputLayoutLitros.setError("!");
            }

        }

        return camposValidos;
    }


}