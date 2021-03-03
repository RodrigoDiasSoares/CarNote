package br.com.CarNote.activity.activitys;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.CarNote.R;
import br.com.CarNote.activity.DataBase.DbHelper;
import br.com.CarNote.activity.DataBase.GastosDAO;
import br.com.CarNote.activity.adapter.AdapterGastos;
import br.com.CarNote.activity.model.Gastos;

public class GastoComOCarroActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterGastos adapter;
    private List<Gastos> listaGastos = new ArrayList<>();
    private Gastos gastoSelecionado;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_com_o_carro);
        dialog = new Dialog(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Gastos GastoSelecionado = listaGastos.get(position);
                                Intent intent = new Intent(GastoComOCarroActivity.this, AddGastosComOCarroActivity.class);
                                intent.putExtra("gastoSelecionado", GastoSelecionado);
                                startActivity(intent);
                            }
                            @Override
                            public void onLongItemClick(View view, int position) {
                                gastoSelecionado = listaGastos.get(position);
                                AlertDialog.Builder dialog = new AlertDialog.Builder(GastoComOCarroActivity.this);
                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir a tarefa: "+gastoSelecionado.getTitulo() + "?");

                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        GastosDAO gastosDAO = new GastosDAO(getApplicationContext());
                                        if(gastosDAO.deletar(gastoSelecionado)){

                                            carregarLista();
                                            Toast.makeText(getApplicationContext(),
                                                    "Tarefa deletada com sucesso!",
                                                    Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(),
                                                    "Erro ao deletar Tarefa!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                dialog.setNegativeButton("Não", null);
                                dialog.create();
                                dialog.show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddGastosComOCarroActivity.class);
                startActivity(intent);
            }
        });
    }
    public void carregarLista(){

        GastosDAO gastosDAO = new GastosDAO(getApplicationContext());
        listaGastos = gastosDAO.listar();
        adapter = new AdapterGastos(listaGastos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        Log.e("INFO","Lista Carregada com sucesso");
    }
    @Override
    protected void onStart() {
        carregarLista();
        super.onStart();
    }
    public void addNovoGasto(){
        dialog.setContentView(R.layout.popup_add_gasto);
    }

}