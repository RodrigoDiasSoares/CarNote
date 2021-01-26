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
    private TextInputLayout textInputLayoutGasto;
    private TextInputLayout textInputLayoutPreco;
    private Button buttonData;
    private Button salvar;
    private Gastos editGasto;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gastos_com_o_carro);
        editTextGasto = findViewById(R.id.textInputEditTextGasto);
        editTextPreco = findViewById(R.id.textInputEditTextPreco);
        textInputLayoutGasto = findViewById(R.id.textInputLayoutGastos);
        textInputLayoutPreco = findViewById(R.id.textInputLayoutPreco);
        buttonData = findViewById(R.id.buttonDate);
        salvar = findViewById(R.id.buttonSalvarGasto);
        editGasto = (Gastos) getIntent().getSerializableExtra("gastoSelecionado");
        buttonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        if(editGasto != null){
            editTextGasto.setText(editGasto.getTitulo());
            editTextPreco.setText(String.valueOf(editGasto.getPreco()));
            buttonData.setText(editGasto.getData());
        }
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GastosDAO gastosDAO = new GastosDAO(getApplicationContext());
                if(editGasto != null){
                    String tituloGasto = editTextGasto.getText().toString();
                    double preco = Double.parseDouble(editTextPreco.getText().toString());
                    Double p = preco;

                    if(!tituloGasto.isEmpty() && p != null){
                        Gastos gastos = new Gastos();
                        gastos.setTitulo(tituloGasto);
                        gastos.setId(editGasto.getId());
                        gastos.setPreco(preco);
                        gastos.setData(data);
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
                    }
                }else {
                    String tituloGasto = editTextGasto.getText().toString();
                    Double p = Double.parseDouble(editTextPreco.getText().toString());


                    if(!tituloGasto.isEmpty() && p != null){
                        Gastos gastos = new Gastos();
                        gastos.setTitulo(tituloGasto);
                        gastos.setPreco(p);
                        gastos.setData(data);
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
                    }
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_to_do, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalvar :

                GastosDAO gastosDAO = new GastosDAO(getApplicationContext());

                if(editGasto != null){
                    String tituloGasto = editTextGasto.getText().toString();
                    double preco = Double.parseDouble(editTextPreco.getText().toString());
                    Double p = preco;

                    if(!tituloGasto.isEmpty() && p != null){
                        Gastos gastos = new Gastos();
                        gastos.setTitulo(tituloGasto);
                        gastos.setId(editGasto.getId());
                        gastos.setPreco(preco);
                        gastos.setData(data);
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
                    }
                }else {
                    String tituloGasto = editTextGasto.getText().toString();
                    Double p = Double.parseDouble(editTextPreco.getText().toString());


                    if(!tituloGasto.isEmpty() && p != null){
                        Gastos gastos = new Gastos();
                        gastos.setTitulo(tituloGasto);
                        gastos.setPreco(p);
                        gastos.setData(data);
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
                    }
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        data = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        buttonData.setText(data);


    }

}