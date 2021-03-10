package br.com.CarNote.activity.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.CarNote.R;
import br.com.CarNote.activity.DataBase.GastosDAO;
import br.com.CarNote.activity.adapter.AdapterGastos;
import br.com.CarNote.activity.model.Gastos;
import br.com.CarNote.activity.model.RecyclerItemClickListener;

public class GastoComOCarroActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private RecyclerView recyclerView;
    private AdapterGastos adapter;
    private List<Gastos> listaGastos = new ArrayList<>();
    private Gastos gastoSelecionado;
    private AdView mAdView;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_com_o_carro);

        recyclerView = findViewById(R.id.recyclerView);
        mAdView = findViewById(R.id.adViewGastosComCarro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                carregarBanner();
            }
        }, 1000);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onLongItemClick(View view, int position) {
                                posicao = position;
                                showPopup(view);

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

    public void carregarBanner(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v, Gravity.RIGHT);
        popup.setOnMenuItemClickListener(this);

        popup.inflate(R.menu.menu_lista_gastos);
        popup.show();
    }

    @Override
    protected void onStart() {
        carregarLista();
        super.onStart();
    }

    public  void editarItem(int position){
        Gastos GastoSelecionado = listaGastos.get(position);
        Intent intent = new Intent(GastoComOCarroActivity.this, AddGastosComOCarroActivity.class);
        intent.putExtra("gastoSelecionado", GastoSelecionado);
        startActivity(intent);
    }

    public void excluiItem(int position) {
        gastoSelecionado = listaGastos.get(position);
        AlertDialog.Builder dialog = new AlertDialog.Builder(GastoComOCarroActivity.this);
        dialog.setTitle("Confirmar exclusão");
        dialog.setMessage("Deseja excluir a tarefa: " + gastoSelecionado.getTitulo() + "?");

        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GastosDAO gastosDAO = new GastosDAO(getApplicationContext());
                if (gastosDAO.deletar(gastoSelecionado)) {

                    carregarLista();
                    Toast.makeText(getApplicationContext(),
                            "Tarefa deletada com sucesso!",
                            Toast.LENGTH_SHORT).show();
                } else {
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
    public boolean onMenuItemClick(MenuItem item) {
        boolean itemMenu = true;
        switch (item.getItemId()){
            case R.id.editar:
                editarItem(posicao);
                itemMenu = true;
                break;
            case R.id.deletar:
                excluiItem(posicao);
                itemMenu = false;
                break;
        }
        return itemMenu;
    }
}