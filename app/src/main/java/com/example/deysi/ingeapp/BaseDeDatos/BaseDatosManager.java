package com.example.deysi.ingeapp.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.deysi.ingeapp.Bean.interfaces.Guardable;

/**
 * Created by Martin on 14/08/2017.
 */

public class BaseDatosManager {
    private static DBHelper db;
    public BaseDatosManager(Context context){
        if (db==null) db = new DBHelper(context);
    }

    public void guardar(Guardable elemento){
        SQLiteDatabase db = this.db.getWritableDatabase();
        db.insert(elemento.getTabla(), null, elemento.getValues());
        db.close();
    }

    public void eliminar(Guardable elemento){
        SQLiteDatabase db = this.db.getWritableDatabase();
        String pars[] = new String[0];
        db.delete(elemento.getTabla(), " where id"+elemento.getTabla()+" = "+elemento.getId(), pars);
        db.close();
    }

    public void modificar(Guardable elemento){
        SQLiteDatabase db = this.db.getWritableDatabase();
        String parm[] = new String[0];
        db.update(elemento.getTabla(), elemento.getValues(), " where id"+elemento.getTabla()+" = "+elemento.getId(), parm);
        db.close();
    }
    public void setearNotas (){
        // todo implementar metodo
        // este metodo obtiene los datos y le asigna todas sus notas
    }
}
