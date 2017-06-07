package com.example.vivian.projetomoraaqui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vivian on 04/06/2017.
 */

public class DatabaseMoreAqui extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_BANCO = "banco";

    public DatabaseMoreAqui(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {

        banco.execSQL("CREATE TABLE more_aqui (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "telefone TEXT NOT NULL, tamanho TEXT NOT NULL, tipo TEXT NOT NULL, em_construcao TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int oldVersion, int newVersion) {
        banco.execSQL("DROP TABLE IF EXISTS more_aqui");
        onCreate(banco);

    }

    public void cadastrarRegistro(MoreAqui m){

        SQLiteDatabase banco = this.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("telefone", m.getTelefone().toString());
        registro.put("tamanho", m.getTamanho());
        registro.put("tipo", m.getTipo());
        registro.put("em_construcao", m.getEmConstrucao());

        banco.insert("more_aqui", null, registro);
        banco.close();
    }

    public Cursor listarRegistros() {
        SQLiteDatabase banco = this.getWritableDatabase();

        Cursor registros = banco.query("more_aqui", null, null, null, null, null, null);
        return registros;
    }
}
