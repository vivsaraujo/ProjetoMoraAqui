package com.example.vivian.projetomoraaqui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button bt_novo;
    private Button bt_mapa;

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
