package com.example.isabel.loginuser;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText name, last_name, correo, password, confirm;
    private Button Guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
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

        name = (EditText) findViewById(R.id.nameEdit);
        last_name = (EditText) findViewById(R.id.apellidoEdit);
        correo = (EditText) findViewById(R.id.EmailEdit);
        password = (EditText) findViewById(R.id.PasswordEdit);
        confirm = (EditText) findViewById(R.id.ConfirmEdit);
        Guardar = (Button) findViewById(R.id.GuardarRegistro);


        SqLiteManager BD2 = new SqLiteManager(this, "REGISTRO", null, 1);

        final SQLiteDatabase Base2 = BD2.getWritableDatabase();


        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correo.getText().toString();
                final String[] args = new String[]{email};
                final Cursor consu = Base2.rawQuery("SELECT email FROM REGISTRO WHERE email=?", args);


                if ((name.getText().toString().trim().isEmpty()) ||
                        (last_name.getText().toString().trim().isEmpty()) ||
                        (correo.getText().toString().trim().isEmpty()) ||
                        (password.getText().toString().trim().isEmpty()) ||
                        (confirm.getText().toString().trim().isEmpty())) {

                    Toast.makeText(Registro.this, "No debes dejar campos vacias", Toast.LENGTH_SHORT).show();


                } else if (consu.moveToFirst()) {
                    Toast.makeText(Registro.this, "Este correo ya existe :" + email + " ", Toast.LENGTH_SHORT).show();

                } else if (confirm.getText().toString().equals(password.getText().toString())) {
                    Base2.execSQL("INSERT INTO REGISTRO (nombre,apellido,email,password) " +
                            "VALUES ('" + name.getText().toString() + "',"
                            + "'" + last_name.getText().toString() + "','"
                            + correo.getText().toString() +
                            "','" + confirm.getText().toString() + "' )");

                    Toast.makeText(Registro.this, "Se guardaron los datos", Toast.LENGTH_SHORT).show();

                    name.setText("");
                    last_name.setText("");
                    correo.setText("");
                    password.setText("");
                    confirm.setText("");


                } else {
                    Toast.makeText(Registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();


                }

            }
        });


    }

}
