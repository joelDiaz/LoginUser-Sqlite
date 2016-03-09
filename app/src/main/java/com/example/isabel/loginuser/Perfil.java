package com.example.isabel.loginuser;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {


    private TextView perfil;
    private ArrayList<String> datos = new ArrayList<>();
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        perfil = (TextView)findViewById(R.id.Perfilusr);

//        perfil.setText(getIntent().getStringExtra("elusuario"));

        for(int i = 0; i<50;i++ ) {
            datos.add("usuario" + i);
        }

        //for (int i = 1; i <= 50; i++)
        //    datos[i - 1] = "usr" + i;

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);


        siguiente = (Button)findViewById(R.id.Siguientes);

        GridView grdOpciones = (GridView) findViewById(R.id.GridOpciones);

        grdOpciones.setAdapter(adaptador);


        grdOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new android.support.v7.app.AlertDialog.Builder(Perfil.this).setTitle("Opcion")
                        .setMessage("Opcion: " + parent.getItemAtPosition(position))
                        .setPositiveButton("OK", null).show();
            }

        });


        
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this,VerificarPreferencias.class);
                startActivity(intent);
            }
        });


    }

}
