package br.com.CarNote.activity.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.CarNote.R;
import br.com.CarNote.activity.DataBase.DbHelper;
import br.com.CarNote.activity.adapter.AdapterGastos;
import br.com.CarNote.activity.model.Gastos;

public class GastoComOCarroActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Gastos> listaGastos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_com_o_carro);
        recyclerView = findViewById(R.id.recyclerView);
        DbHelper db = new DbHelper(getApplicationContext());
        AdapterGastos adapter = new AdapterGastos();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditGastosComOCarroActivity.class);
                startActivity(intent);
            }
        });
    }
    
}