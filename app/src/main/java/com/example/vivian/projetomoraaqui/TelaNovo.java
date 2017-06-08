package com.example.vivian.projetomoraaqui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


public class TelaNovo extends AppCompatActivity {

    private EditText edt_fone;

    private Button bt_limpar;
    private Button bt_pronto;

    private Spinner spin_tamanho;
    private Spinner spin_tipo;

    private  ArrayAdapter adapterTamanho;
    private  ArrayAdapter adapterTipo;

    private Switch swt_opcao;

    private DatabaseMoreAqui banco;
    private MaskEditTextChangedListener maskTEL;

    String opcao;
    String itemTipoSelecionado;
    String itemTamanhoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_novo);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // cast dos componentes editText, Switch, Spinner, Button...
        spin_tamanho = (Spinner) findViewById(R.id.spin_tamanho);
        spin_tipo = (Spinner) findViewById(R.id.spin_tipo);

        //cast do editText telefone setando para esse campo uma máscara para digitalização no campo
        edt_fone = (EditText) findViewById(R.id.edt_fone);
        maskTEL = new MaskEditTextChangedListener("(##)#####-####", edt_fone);
        edt_fone.addTextChangedListener(maskTEL);

        bt_limpar = (Button) findViewById(R.id.bt_limpar);
        bt_pronto = (Button) findViewById(R.id.bt_pronto);

        swt_opcao= (Switch) findViewById(R.id.swt_opcao);

        banco = new DatabaseMoreAqui(this);

        // Add navigation com icone voltar com onClick para chamar a anterior
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTexto));
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.voltar);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(TelaNovo.this);
                }
            });

        }

        //chama o metodo limpas dados para limpar todos os componentes da tela
        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparDados(v);
            }
        });

        /*realiza verificação se campo telefone esta vazio, caso sim mostra um erro com msg "Campo Obrigatorio"
        senão, grava os registro no banco atraves do metodo cadastrarRegistro, limpa o campo telefone, mostra
        uma msg na tela informando que registros foram inseridos com sucesso, e chama a outra activty carregando
         a lista das informações*/
        bt_pronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (edt_fone.getText().toString().equals("")) {
                        edt_fone.setError("Campo Obrigatório");
                        edt_fone.requestFocus();

                    } else {

                        MoreAqui m = new MoreAqui();
                        m.setTelefone(edt_fone.getText().toString());
                        m.setTamanho(spin_tamanho.getSelectedItem().toString());
                        m.setTipo(spin_tipo.getSelectedItem().toString());
                        m.setEmConstrucao(opcao.toString());
                        banco.cadastrarRegistro(m);

                        edt_fone.setText(null);

                        Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(TelaNovo.this, ListaActivity.class);
                        startActivity(i);
                    }
            }


            //String telefone = edt_fone.getText().toString();

            //  if(telefone == null || telefone.equals("")) {
            //    edt_fone.setError("Campo telefone obrigatório");
            //  return;

            //} else{
//
            //                  Toast.makeText(TelaNovo.this, "Informações salvas com sucesso", Toast.LENGTH_SHORT).show();
            //                Intent telaAnterior  = new Intent(TelaNovo.this, MainActivity.class);
            //             startActivity(telaAnterior);

            //      }
            // }
        });

        /*verifica se o switch foi marcado ou não, se sim é definido um textOn para switch sendo
        armazenado como uma String para ser gravado no banco, se não é definido um textOff para
        switch sendo armazenado como uma String para ser gravado no banco*/
        swt_opcao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(swt_opcao.isChecked()){
                    swt_opcao.setTextOn("Sim");
                    opcao = swt_opcao.getTextOn().toString();
                    Log.i("MeuLogX", opcao);
                }else{
                    swt_opcao.setTextOff("Não");
                    opcao = swt_opcao.getTextOff().toString();
                    Log.i("MeuLogX", opcao);
                }
            }
        });

        /*define que o item selecionado tem que ser maior que a posição 0, o item selecionado
        é armazenado numa String para ser registrado no banco */
        spin_tamanho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    if(position > 0) {
                        itemTamanhoSelecionado = spin_tamanho.getSelectedItem().toString();
                        Log.i("MeuLogX", itemTamanhoSelecionado);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*define que o item selecionado tem que ser maior que a posição 0, o item selecionado
        é armazenado numa String para ser registrado no banco */
        spin_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try{
                    if(position > 0) {
                        itemTipoSelecionado = spin_tipo.getSelectedItem().toString();
                        Log.i("MeuLogX", itemTipoSelecionado);
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

    /*carrega layout array list criado na pasta /Values/Strings,
    carrega layout nativo do android*/
    public void carregaSpinnerTamanho() {

        adapterTamanho = ArrayAdapter.createFromResource(this,R.array.tamanho_array_list,
                android.R.layout.simple_spinner_item);
        adapterTamanho.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_tamanho.setAdapter(new NothingSelectedSpinnerAdapter(adapterTamanho,
                R.layout.spinner_tamanho_nothing_selected,
                this));
    }
    /*carrega layout array list criado na pasta /Values/Strings,
       carrega layout nativo do android*/
    public void carregaSpinnerTipo(){

        adapterTipo = ArrayAdapter.createFromResource(this,R.array.tipo_array_list,
                android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_tipo.setAdapter(new NothingSelectedSpinnerAdapter(adapterTipo,
                R.layout.spinner_tipo_nothing_selected,
                this));

    }
        //metodo limpa todos os componentes da tela
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

