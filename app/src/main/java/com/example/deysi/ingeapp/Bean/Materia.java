/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.deysi.ingeapp.Bean;

import android.content.ContentValues;

import com.example.deysi.ingeapp.Bean.interfaces.Guardable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Martin
 */
public class Materia extends Listable implements Guardable {
    private final int id;
    private int semestre;
    private char carrera;
    private String nombre;

    public Materia(int id, int semestre, char carrera, String nombre) {
        this.id = id;
        this.semestre = semestre;
        this.carrera = carrera;
        this.nombre = nombre;
    }

    public Materia(int idmateria) {
        id = idmateria;
        semestre = 0;
        carrera = 'N';
        nombre = "Error de carrera";
    }

    public Materia(JSONObject jmateria) throws JSONException {
        id = jmateria.getInt("id");
        carrera = jmateria.has("career")?jmateria.getJSONObject("career").getString("description").charAt(0):'N';
        semestre = jmateria.getInt("semester");
        nombre = jmateria.getString("name");
    }

    @Override
    public String getTitulo() {
        return nombre;
    }

    @Override
    public String getFecha() {
        return "";
    }

    @Override
    public char getTipo() {
        return Listable.MATERIA;
    }

    @Override
    public String getMateria() {
        return nombre;
    }

    public int getSemestre() {
        return semestre;
    }


    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public char getCarrera() {
        return carrera;
    }

    public void setCarrera(char carrera) {
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String descripcion) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }


    @Override
    public String getTabla() {
        return "materias";
    }

    @Override
    public ContentValues getValues() {
        ContentValues v = new ContentValues();
        v.put("idmaterias", getId());
        v.put("nombre", getNombre());
        v.put("semestre", getSemestre());
        v.put("carrera", carrera+"");
        return v;
    }

    @Override
    public String toString() {
        return "Materia{" + "id=" + id + ", semestre=" + semestre + ", nombre=" + nombre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Materia other = (Materia) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}