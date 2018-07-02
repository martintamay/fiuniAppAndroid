package com.example.deysi.ingeapp.BaseDeDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.deysi.ingeapp.Bean.Alumno;
import com.example.deysi.ingeapp.Bean.Materia;
import com.example.deysi.ingeapp.Bean.Nota;
import com.example.deysi.ingeapp.Bean.PuntosParciales;
import com.example.deysi.ingeapp.Bean.interfaces.Guardable;

import java.util.ArrayList;

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
        db.insertWithOnConflict(elemento.getTabla(), null, elemento.getValues(), SQLiteDatabase.CONFLICT_REPLACE);
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

    public void setearMaterias (){
        // todo implementar metodo
        // este metodo obtiene los datos y le asigna todas sus materias
    }
    public void setearNotas (){
        // todo implementar metodo
        // este metodo obtiene los datos y le asigna todas sus notas
    }

    public Alumno getAlumno() {
        SQLiteDatabase rdb = db.getReadableDatabase();
        String[] campos = new String[]{"idalumno", "nombres", "ci", "correo", "ingreso", "carrera"};
        Cursor c = rdb.query("alumno", campos, "", new String[]{}, null, null, null);
        if (c.moveToFirst()){
            Alumno al = new Alumno(c.getInt(0), c.getString(1), c.getString(2),
                    c.getString(3), c.getInt(4), c.getString(5));
            return al;
        }
        return null;
    }

    public ArrayList<Materia> getMaterias() {
        SQLiteDatabase rdb = db.getReadableDatabase();
        String[] campos = new String[]{"idmaterias", "semestre", "carrera", "nombre"};
        Cursor c = rdb.query("materias", campos, "", new String[]{}, null, null, null);
        ArrayList<Materia> materias = new ArrayList();
        while (c.moveToNext()){
            Materia mat = new Materia(c.getInt(0), c.getInt(1), c.getString(2).charAt(0), c.getString(3));
            materias.add(mat);
            if(Datos.ALUMNO!=null) Datos.ALUMNO.agregarMateria(mat);
        }
        return materias;
    }

    public void getNotas() {
        SQLiteDatabase rdb = db.getReadableDatabase();

        //se obtienen las notas
        String[] camposN = new String[]{"idnotas", "materias_idmaterias", "puntaje", "fecha", "mesa"};
        Cursor c = rdb.query("notas", camposN, "", new String[]{}, null, null, null);
        while (c.moveToNext()){
            Nota n = new Nota(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3), c.getInt(4));
            if(Datos.ALUMNO!=null) Datos.ALUMNO.agregarNota(n);
        }

        //se obtienen los pps
        String[] camposP = new String[]{"idpuntosparciales", "materias_idmaterias", "puntaje", "fecha"};
        c = rdb.query("puntosparciales", camposP, "", new String[]{}, null, null, null);
        while (c.moveToNext()){
            PuntosParciales p = new PuntosParciales(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3));
            if(Datos.ALUMNO!=null) Datos.ALUMNO.agregarPp(p);
        }
    }
}
