package com.example.vivian.projetomoraaqui;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class TelaNovo extends AppCompatActivity {

    private EditText edt_fone;

    private Button bt_limpar;
    private Button bt_pronto;

    private Spinner spin_tamanho;
    private Spinner spin_tipo;

    private  ArrayAdapter adapterTamanho;
    private  ArrayAdapter adapterTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_novo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spin_tamanho = (Spinner) findViewById(R.id.spin_tamanho);
        spin_tipo = (Spinner) findViewById(R.id.spin_tipo);
        edt_fone = (EditText) findViewById(R.id.edt_fone);
        bt_limpar = (Button) findViewById(R.id.bt_limpar);
        bt_pronto = (Button) findViewById(R.id.bt_pronto);


        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTexto));
        if (toolbar != null) {
            toolbar.setTitle("Novo");
            toolbar.setNavigationIcon(R.drawable.voltar);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(TelaNovo.this);
                }
            });

        }

        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparDados(v);
            }
        });

        spin_tamanho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    if(position > 0) {
                        String itemSelecionado = spin_tamanho.getSelectedItem().toString();
                        Log.i("MeuLogX", itemSelecionado);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bt_pronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(TelaNovo.this, "Salvo", Toast.LENGTH_SHORT).show();
            }
        });

        spin_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    if(position > 0) {
                        String itemSelecionado = spin_tipo.getSelectedItem().toString();
                        Log.i("MeuLogTipo", itemSelecionado);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        carregaSpinnerTamanho();
        carregaSpinnerTipo();

    }


    public void carregaSpinnerTamanho() {

        adapterTamanho = ArrayAdapter.createFromResource(this,R.array.tamanho_array_list,
                android.R.layout.simple_spinner_item);
        adapterTamanho.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_tamanho.setAdapter(new NothingSelectedSpinnerAdapter(adapterTamanho,
                R.layout.spinner_tamanho_nothing_selected,
                this));
    }

    public void carregaSpinnerTipo(){

        adapterTipo = ArrayAdapter.createFromResource(this,R.array.tipo_array_list,
                android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_tipo.setAdapter(new NothingSelectedSpinnerAdapter(adapterTipo,
                R.layout.spinner_tipo_nothing_selected,
                this));

    }

    public void limparDados(View view){

        edt_fone.setText(null);

        spin_tamanho.setAdapter(new NothingSelectedSpinnerAdapter(adapterTamanho,
                R.layout.spinner_tamanho_nothing_selected,
                this));

        spin_tipo.setAdapter(new NothingSelectedSpinnerAdapter(adapterTipo,
                R.layout.spinner_tipo_nothing_selected,
                this));

    }
}

