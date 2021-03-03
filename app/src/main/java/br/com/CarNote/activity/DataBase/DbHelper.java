package br.com.CarNote.activity.DataBase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "db_gastos";
    public static String TABELA_GASTOS = "db_gastos";

    public DbHelper(Context context) {
        super(context,NOME_DB,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists "+TABELA_GASTOS
                +" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                " , titulo varchar not null, preco varchar not null, data varchar not null);";
        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
