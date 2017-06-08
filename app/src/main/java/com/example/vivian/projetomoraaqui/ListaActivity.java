package com.example.vivian.projetomoraaqui;

import android.database.Cursor;
import android.os.Bundle;
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


// Add icone voltar com onClick para chamar a anterior
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

        // Um cast da ListView
        //Instancia da Classe do Banco de dados
        //chama metodo mostrar lista para mostrar na tela a lista de informações
        lvRegistros = (ListView) findViewById(R.id.lv_Listar);
        banco = new DatabaseMoreAqui(this);
        montarListaRegistro();
    }

    @SuppressWarnings("deprecation")
    private void montarListaRegistro() {

        //Foi usado um SimpleCursosAdapter para recuperar os registros e adicioná-los ao componente ListView
        //Criei um Layout XML personalizado(three_Line_List_item) para mostrar os registro na ListView
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
