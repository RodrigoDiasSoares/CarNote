package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
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

public class AlcoolOuGasolina extends AppCompatActivity {
    private TextInputEditText editTextGasolina;
    private TextInputEditText editTextAlcool;
    private TextInputLayout textInputLayoutGasolina;
    private TextInputLayout textInputLayoutAcool;
    private Button buttonCalcular;
    private AdView mAdView;


    private FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_ou_gasolina);
        editTextGasolina = findViewById(R.id.TextInputEditTextGasolina);
        editTextAlcool = findViewById(R.id.TextInputEditTextAlcool);
        textInputLayoutGasolina = findViewById(R.id.textInputLayoutGasolina);
        textInputLayoutAcool = findViewById(R.id.textInputLayoutAlcool);
        buttonCalcular = findViewById(R.id.buttonCalcularAlcoolOuGasolina);
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


}