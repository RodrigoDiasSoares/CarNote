package br.com.CarNote.activity.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.CarNote.R;
import br.com.CarNote.activity.DataBase.GastosDAO;
import br.com.CarNote.activity.model.Gastos;

public class AddGastosComOCarroActivity extends AppCompatActivity {
    private TextInputEditText editTextGasto;
    private TextInputEditText editTextPreco;
    private TextInputLayout textInputLayoutGasto;
    private TextInputLayout textInputLayoutPreco;
    private EditText editTextData;
    private Button salvar;
    private Gastos editGasto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gastos_com_o_carro);
        editTextGasto = findViewById(R.id.textInputEditTextGasto);
        editTextPreco = findViewById(R.id.textInputEditTextPreco);
        textInputLayoutGasto = findViewById(R.id.textInputLayoutGastos);
        textInputLayoutPreco = findViewById(R.id.textInputLayoutPreco);
        editTextData = findViewById(R.id.editTextDate);
        salvar = findViewById(R.id.buttonSalvarGasto);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GastosDAO gastosDAO = new GastosDAO(getApplicationContext());
                if(editGasto != null){
                    String tituloGasto = editTextGasto.getText().toString();
                    double preco = Double.parseDouble(editTextPreco.getText().toString());
                    Double p = preco;
                    String detalhes = editTextData.getText().toString();
                    if(!tituloGasto.isEmpty() && p != null){
                        Gastos gastos = new Gastos();
                        gastos.setTitulo(tituloGasto);
                        gastos.setId(editGasto.getId());
                        gastos.setPreco(preco);
                        gastos.setData(detalhes);
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
                    String detalhes = editTextData.getText().toString();

                    if(!tituloGasto.isEmpty() && p != null){
                        Gastos gastos = new Gastos();
                        gastos.setTitulo(tituloGasto);
                        gastos.setPreco(p);
                        gastos.setData(detalhes);
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
                    String detalhes = editTextData.getText().toString();
                    if(!tituloGasto.isEmpty() && p != null){
                        Gastos gastos = new Gastos();
                        gastos.setTitulo(tituloGasto);
                        gastos.setId(editGasto.getId());
                        gastos.setPreco(preco);
                        gastos.setData(detalhes);
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
                    String detalhes = editTextData.getText().toString();

                    if(!tituloGasto.isEmpty() && p != null){
                        Gastos gastos = new Gastos();
                        gastos.setTitulo(tituloGasto);
                        gastos.setPreco(p);
                        gastos.setData(detalhes);
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

}