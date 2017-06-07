package com.example.vivian.projetomoraaqui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaActivity extends AppCompatActivity {

    private ListView lvRegistros;
    private DatabaseMoreAqui banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTexto));
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.voltar);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(ListaActivity.this);
                }
            });

        }

        lvRegistros = (ListView) findViewById(R.id.lv_Listar);
        banco = new DatabaseMoreAqui(this);
        montarListaRegistro();
    }

    @SuppressWarnings("deprecation")
    private void montarListaRegistro() {

        Cursor resistros = banco.listarRegistros();
        String nomeCamposTabela[] = new String[] {"telefone", "tamanho", "tipo", "em_construcao"};
        int nomeCamposTela[] = new int[] {R.id.telefone, R.id.tamanho, R.id.tipo, R.id.em_construcao};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.three_line_list_item,
                resistros,
                nomeCamposTabela,
                nomeCamposTela);


        lvRegistros.setAdapter(adapter);
    }

}
