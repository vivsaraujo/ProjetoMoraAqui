package com.example.vivian.projetomoraaqui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button bt_novo;
    private Button bt_mapa;
    private Button bt_procurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //cast do botao  novo/ chama tela TELANOVO
        bt_novo = (Button) findViewById(R.id.bt_novo);
        bt_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TelaNovo.class);
                startActivity(intent);
            }
        });

        bt_procurar = (Button) findViewById(R.id.bt_procurar);
        bt_procurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ListaActivity.class);
                startActivity(i);
            }
        });


        //cast do botao  novo/ chama tela MAPSACTIVITY
        bt_mapa = (Button) findViewById(R.id.bt_mapa);
        bt_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });


    }
}
