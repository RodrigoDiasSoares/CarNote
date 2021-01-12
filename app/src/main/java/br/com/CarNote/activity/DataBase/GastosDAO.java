package br.com.CarNote.activity.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import br.com.CarNote.activity.model.Gastos;

public class GastosDAO implements iGastosDAO{
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public GastosDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Gastos gastos) {
        ContentValues cv = new ContentValues();
        cv.put("titulo",gastos.getTitulo());
        cv.put("preco",gastos.getPreco());
        cv.put("detalhes",gastos.getDetalhes());
        try {
            escrever.insert(DbHelper.TABELA_GASTOS,null,cv);
            Log.e("INFO","Sucesso ao salvar tarefa ");
        }catch (Exception e){
            Log.e("INFO","Erro ao salvar tarefa "+ e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Gastos gastos) {
        return false;
    }

    @Override
    public boolean deletar(Gastos gastos) {
        return false;
    }

    @Override
    public List<Gastos> listar() {
        return null;
    }
}
