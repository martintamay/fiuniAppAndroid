package com.example.deysi.ingeapp.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Martin on 13/08/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dbIngeApp";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        for (int i=0; i<TABLAS.length; i++){
            db.execSQL(TABLAS[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static String TABLAS [] = {

            "CREATE TABLE alumno (\n" +
            "  idalumno INT NOT NULL,\n" +
            "  nombres TEXT NOT NULL,\n" +
            "  ci TEXT NOT NULL,\n" +
            "  correo TEXT NOT NULL,\n" +
            "  contrasenha TEXT NOT NULL,\n" +
            "  ingreso INT NOT NULL,\n" +
            "  carrera TEXT NOT NULL,\n" +
            "  PRIMARY KEY (idalumno))",

            "CREATE TABLE notas (\n" +
            "  idnotas INT NOT NULL,\n" +
            "  fecha TEXT NOT NULL,\n" +
            "  puntaje INT NOT NULL,\n" +
            "  materia TEXT NOT NULL,\n" +
            "  alumno_idalumno INT NOT NULL,\n" +
            "  PRIMARY KEY (idnotas))\n",

            "CREATE TABLE puntosparciales (\n" +
            "  idpuntosparciales INT NOT NULL,\n" +
            "  fecha TEXT NOT NULL,\n" +
            "  puntaje INT NOT NULL,\n" +
            "  materia TEXT NOT NULL,\n" +
            "  alumno_idalumno INT NOT NULL,\n" +
            "  PRIMARY KEY (idpuntosparciales))"
    };
}
