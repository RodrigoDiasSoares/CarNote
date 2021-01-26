package br.com.CarNote.activity.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
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
        cv.put("data",gastos.getData());
        try {
            escrever.insert(DbHelper.TABELA_GASTOS,null,cv);
            Log.i("INFO","Sucesso ao salvar Gasto ");
        }catch (Exception e){
            Log.e("INFO","Erro ao salvar Gasto "+ e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Gastos gastos) {
        ContentValues cv = new ContentValues();
        cv.put("titulo",gastos.getTitulo());
        cv.put("preco",gastos.getPreco());
        cv.put("detalhes",gastos.getData());
        try {
            String[] args = {gastos.getId().toString()};
            escrever.update(DbHelper.TABELA_GASTOS,cv,"id=?" ,args);
            Log.i("INFO","Sucesso ao Atulizar Gasto ");
        }catch (Exception e){
            Log.e("INFO","Erro ao Atulizar Gasto "+ e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Gastos gastos) {
        try {
            String[] args = {gastos.getId().toString()};
            escrever.delete(DbHelper.TABELA_GASTOS,"id=?",args);
            Log.i("INFO","Sucesso ao Deletar Gasto ");
        }catch (Exception e){
            Log.e("INFO","Erro ao Deletar Gasto "+ e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Gastos> listar() {
        List<Gastos> gastosList = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_GASTOS + " ;";
        Cursor cursor = ler.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Gastos gastos = new Gastos();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String tituloGasto = cursor.getString(cursor.getColumnIndex("titulo"));
            double precoGasto = cursor.getDouble(cursor.getColumnIndex("preco"));
            String dethalheGasto = cursor.getString(cursor.getColumnIndex("detalhes"));
            gastos.setId(id);
            gastos.setTitulo(tituloGasto);
            gastos.setPreco(precoGasto);
            gastos.setData(dethalheGasto);
            gastosList.add(gastos);
            Log.i("DB", ""+gastos.getTitulo());
        }
        return gastosList;
    }
}
