package br.com.CarNote.activity.activitys;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

import br.com.CarNote.R;

public class ConsumoPorLitroActivity extends AppCompatActivity {
    private EditText editTextkm;
    private EditText editTextLitros;
    private TextInputLayout textInputLayoutLitros;
    private TextInputLayout textInputLayoutKm;
    private Button buttonCalcularKmPorLitro;
    private Dialog dialog;
    private AdView mAdView;
    private AdView mAdView2;

    private FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo_por_litro);
        editTextkm = findViewById(R.id.TextInputEditTextKm);
        editTextLitros = findViewById(R.id.TextInputEditTextLitros);
        textInputLayoutKm = findViewById(R.id.textInputLayoutKm);
        textInputLayoutLitros = findViewById(R.id.textInputLayoutLitros);
        buttonCalcularKmPorLitro = findViewById(R.id.buttonCalcularConsumo);
        mAdView = findViewById(R.id.adViewConsumoPorLitro);
        mAdView2 = findViewById(R.id.adViewConsumoPorLitro2);
        dialog = new Dialog(this);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                carregarBanner();
            }
        }, 1000);

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
        buttonCalcularKmPorLitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularKmPorLitro(v);
            }
        });


    }

    public void calcularKmPorLitro(View view) {
        String litros = editTextLitros.getText().toString();
        String km = editTextkm.getText().toString();

        if (validarCampos(litros, km)) {
            try {
                equação(litros, km);
            } catch (NumberFormatException e) {
                litros = litros.replace(',', '.');
                km = km.replace(',', '.');
                equação(litros, km);
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Preencha os campos",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void equação(String litros, String km) {
        // verificação nescessaria devido a um espaço que vem no inicio do input

        if ( !Character.isDigit(litros.charAt(0)) ) {

            litros = litros.substring (1);
        }
        if ( !Character.isDigit(km.charAt(0)) ) {
            km = km.substring (1);
        }

        // fim da verificação

        double valorlitros = Double.parseDouble(litros);
        double valorKm = Double.parseDouble(km);
        Double resultadoEquacao = valorKm / valorlitros;


        DecimalFormat df = new DecimalFormat("#,###.00");
        resultado(df.format(resultadoEquacao));

    }


    public boolean validarCampos(String litros, String km) {
        boolean camposValidos = true;
        if (litros.equals("") || litros == null) {
            textInputLayoutLitros.setError("!");
            camposValidos = false;
        } else {
            textInputLayoutLitros.setErrorEnabled(false);
        }
        if (km.equals("") || km == null) {
            textInputLayoutKm.setError("!");
            camposValidos = false;
        } else {
            textInputLayoutKm.setErrorEnabled(false);
        }

        return camposValidos;
    }
    public void resultado(String consumo){
        dialog.setContentView(R.layout.popup_consumo);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textView = dialog.findViewById(R.id.textViewPopupConsumo);
        textView.setText(consumo+" Km");
        dialog.show();
    }
    public void carregarBanner(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView2.loadAd(adRequest);
    }


}