package com.example.isabel.loginuser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Isabel on 7/3/16.
 */
public class SqLiteManager extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE DATABASE (ID Integer PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT)";
    String sqlCreate2 = "CREATE TABLE REGISTRO (ID Integer PRIMARY KEY AUTOINCREMENT, nombre TEXT,apellido TEXT, email TEXT,password varchar(20) NOT NULL)";


    //
    public SqLiteManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL ejectuta un query SQL
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);// Segunta tabla de registro de usuarios

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Elimina la base de datos
        db.execSQL("DROP TABLE IF EXISTS DATABASE");
        db.execSQL("DROP TABLE IF EXISTS REGISTRO");


        // Crea la base de datos nuevamente
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);

    }


}
