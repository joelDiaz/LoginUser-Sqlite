package com.example.isabel.loginuser;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerificarPreferencias extends AppCompatActivity {


    private TextView prf1,prf2;
    private EditText nombre,email;
    private Button Guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_preferencias);
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

        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String usuarios = prefe.getString("usuario", "");
        String passwt = prefe.getString("passw", "");
        prf1 = (TextView)findViewById(R.id.preferido);
        prf2 = (TextView)findViewById(R.id.preferido2);
        nombre = (EditText)findViewById(R.id.nombre);
        email = (EditText)findViewById(R.id.email);
        Guardar = (Button)findViewById(R.id.Guardar);

        prf1.setText(usuarios);
        prf2.setText(passwt);


        SqLiteManager BD= new SqLiteManager(this,"DATABASE",null,1);

        final SQLiteDatabase Base = BD.getWritableDatabase();

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Base != null){
                    Base.execSQL("INSERT INTO DATABASE (nombre, email) " +
                            "VALUES ('" + nombre.getText().toString() +"'," +
                            "'"+email.getText().toString()+"' )" );



//                    Base.close();
                    Toast.makeText(VerificarPreferencias.this, "Se guardaron los datos", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    email.setText("");


                }
            }
        });





    }

}
