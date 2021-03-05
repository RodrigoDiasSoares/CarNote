package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.CarNote.R;
import me.abhinay.input.CurrencyEditText;


public class AlcoolOuGasolina extends AppCompatActivity {
    private CurrencyEditText editTextGasolina;
    private CurrencyEditText editTextAlcool;
    private TextInputLayout textInputLayoutGasolina;
    private TextInputLayout textInputLayoutAcool;
    private Dialog dialog;
    private Button buttonCalcular;
    private AdView mAdView;
    private TextView textViewResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_ou_gasolina);
        editTextGasolina = findViewById(R.id.TextInputEditTextGasolina);
        editTextAlcool = findViewById(R.id.TextInputEditTextAlcool);
        textInputLayoutGasolina = findViewById(R.id.textInputLayoutGasolina);
        textInputLayoutAcool = findViewById(R.id.textInputLayoutAlcool);
        buttonCalcular = findViewById(R.id.buttonCalcularAlcoolOuGasolina);
        editTextAlcool.setSpacing(false);

        dialog = new Dialog(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewAlcoolOuGasolina);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



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
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularPreco(v);
            }
        });

    }

    public void calcularPreco(View view){

        String precoAlcool = editTextAlcool.getText().toString();
        String precoGasolina = editTextGasolina.getText().toString();


        if(validarCampos(precoAlcool, precoGasolina)){
            try {

                equação(precoAlcool, precoGasolina);

            }catch (NumberFormatException e){

                precoAlcool = precoAlcool.replace(',','.');
                precoGasolina = precoGasolina.replace(',' , '.');
                equação(precoAlcool, precoGasolina);
            }
        }else {
            Toast.makeText(getApplicationContext(),
                    "Preencha os campos",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void equação(String precoAlcool, String precoGasolina){

        // verificação nescessaria devido a um espaço que vem no inicio do input

        if ( !Character.isDigit(precoAlcool.charAt(0)) ) {

            precoAlcool = precoAlcool.substring (1);
        }
        if ( !Character.isDigit(precoGasolina.charAt(0)) ) {
            precoGasolina = precoGasolina.substring (1);
        }

        // fim da verificação

        double valorAlcool = Double.parseDouble(precoAlcool);
        double valorGasolina = Double.parseDouble(precoGasolina);
        double valorBase = valorGasolina * 0.7;

        String combustivel;

        if(valorAlcool <= valorBase){

            combustivel = "Álcool";

            resultado(combustivel);
        }else{

            combustivel = "Gasolina";

            resultado(combustivel);
        }
    }


    public boolean validarCampos(String precoAlcool, String precoGasolina){
        boolean camposValidos = true;

        if(precoAlcool.equals("") || precoAlcool == null){

            textInputLayoutAcool.setError("!");
            camposValidos = false;


        }else{

            textInputLayoutAcool.setErrorEnabled(false);
        }

        if(precoGasolina.equals("") || precoGasolina == null){

            textInputLayoutGasolina.setError("!");
            camposValidos = false;


        }else{

            textInputLayoutGasolina.setErrorEnabled(false);
        }

        return camposValidos;
    }
    public void resultado(String combustivel){
        dialog.setContentView(R.layout.popup_gasolina);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        textViewResultado = dialog.findViewById(R.id.textViewPopup);
        textViewResultado.setText(combustivel);

        dialog.show();

    }


}