package br.com.CarNote.activity.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.Calendar;

import br.com.CarNote.R;
import br.com.CarNote.activity.DataBase.GastosDAO;
import br.com.CarNote.activity.framents.DatePickerFragment;
import br.com.CarNote.activity.model.Gastos;

public class AddGastosComOCarroActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private TextInputEditText editTextGasto;
    private TextInputEditText editTextPreco;
    private TextInputEditText editTextData;
    private TextInputLayout textInputLayoutGasto;
    private TextInputLayout textInputLayoutPreco;
    private TextInputLayout textInputLayoutData;
    private Button salvar;
    private Gastos editGasto;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gastos_com_o_carro);
        editTextGasto = findViewById(R.id.textInputEditTextGasto);
        editTextPreco = findViewById(R.id.textInputEditTextPreco);
        editTextData = findViewById(R.id.textInputEditTextData);
        textInputLayoutGasto = findViewById(R.id.textInputLayoutGastos);
        textInputLayoutPreco = findViewById(R.id.textInputLayoutPreco);
        textInputLayoutData = findViewById(R.id.textInputLayoutData);
        salvar = findViewById(R.id.buttonSalvarGasto);

        editGasto = (Gastos) getIntent().getSerializableExtra("gastoSelecionado");
        if(editGasto != null) {
            editTextGasto.setText(editGasto.getTitulo());
            editTextPreco.setText(String.valueOf(editGasto.getPreco()));
            editTextData.setText(editGasto.getData());
        }


        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });


        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GastosDAO gastosDAO = new GastosDAO(getApplicationContext());
                Gastos gastos = new Gastos();
                if(editGasto != null){
                    String tituloGasto = editTextGasto.getText().toString();
                    Double preco = Double.parseDouble(editTextPreco.getText().toString());
                    String data = editTextData.getText().toString();

                    if(validarCampos(tituloGasto,preco.toString(),data)){
                        gastos = salvarGasto(tituloGasto,preco.toString(),data);
                        gastos.setId(editGasto.getId());
                        if(gastosDAO.atualizar(gastos)){

                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao atualizar a Tarefa",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Erro ao atualizar a Tarefa",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Preencha os campos",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{

                    String tituloGasto = editTextGasto.getText().toString();


                    if(validarCampos(tituloGasto,editTextPreco.getText().toString(),data)){
                        gastos = salvarGasto(tituloGasto,editTextPreco.getText().toString(),data);

                        if (gastosDAO.salvar(gastos)){
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao salvar a Tarefa",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Erro ao salvar a Tarefa",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Preencha os campos",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        data = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        editTextData.setText(data);


    }
    public boolean validarCampos(String editGastos, String editPreco, String editData){
        boolean camposValidos = true;

        if(editGastos.equals("")){
            textInputLayoutGasto.setError("!");
            camposValidos = false;
        }else{
            textInputLayoutGasto.setErrorEnabled(false);
        }
        if(editPreco.equals("")){
            textInputLayoutPreco.setError("!");
            camposValidos = false;
        }else{
            textInputLayoutPreco.setErrorEnabled(false);
        }
        if(editData == null){
            textInputLayoutData.setError("!");
            camposValidos = false;
        }else {
            textInputLayoutData.setErrorEnabled(false);
        }


        return camposValidos;

    }
    public Gastos salvarGasto(String tituloGasto, String preco, String data){
        Gastos gastos = new Gastos();

        gastos.setTitulo(tituloGasto);
        gastos.setData(data);
        try {
            gastos.setPreco(Double.parseDouble(preco));

        }catch (NumberFormatException e){
            preco = preco.replace(',','.');
            gastos.setPreco(Double.parseDouble(preco));
        }


        return gastos;
    }

}