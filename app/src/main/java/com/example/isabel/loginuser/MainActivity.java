package com.example.isabel.loginuser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText user,pswd;
    private Button Entrar,Registrar;
    private String usr="Developer",pass="18mayo1991";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        SqLiteManager BD= new SqLiteManager(this,"DATABASE",null,1);

        SQLiteDatabase Base = BD.getWritableDatabase();

        // BASE DE DATOS DE LOS USUARIOS DE LA APLICACION
        SqLiteManager BD2= new SqLiteManager(this,"REGISTRO",null,1);

        final SQLiteDatabase Base2 = BD2.getWritableDatabase();




//
//        if (Base !=null){
//
//            for(int i = 1 ; i<6 ; i++){
//                int codigo =i;
//                String nombre = "usuario"+i;
//                String email = nombre+"@gmail.com";
//
//                Base.execSQL("INSERT INTO DATABASE (nombre, email) " +
//                        "VALUES ('" + nombre +"','"+email+"' )" );
//
//
//            }
//
//            Base.close();
//
//
//        }



        user = (EditText)findViewById(R.id.Usuario);
        pswd = (EditText)findViewById(R.id.Password);
        Entrar = (Button)findViewById(R.id.Entrar);
        Registrar = (Button)findViewById(R.id.Registrar_nuevo);


        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] campos = new String[] {"email", "password"};
                final String values = user.getText().toString();
                final String[] email = new String[]{values};
                String emailr="";
                String passd ="";
                Cursor query = Base2.query("REGISTRO",campos,"email=?",email,null,null,null);

                if (query.moveToFirst()){

                    do {
                        emailr = query.getString(0);
                        passd = query.getString(1);

                    }while (query.moveToNext());

                }



                if ((user.getText().toString().equals(emailr))&&(pswd.getText().toString().equals(passd))){
                    Intent intent = new Intent(MainActivity.this,Perfil.class);
                    Bundle datos = new Bundle();
                    datos.putString("elusuario",user.getText().toString());
                    intent.putExtras(datos);
                    startActivity(intent);

                }else{
//                    new AlertDialog.Builder(MainActivity.this).setTitle("Error")
//                            .setMessage(pass)
//                            .setPositiveButton("OK", null).show();

                    SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editos = preferencias.edit();
                    editos.putString("usuario",user.getText().toString());
                    editos.putString("passw",pswd.getText().toString());
                    editos.commit();

                    Toast.makeText(MainActivity.this, "Usuario o Password incorrecto", Toast.LENGTH_SHORT).show();

                }
                }


        });

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Registro.class);
                startActivity(intent);
            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
