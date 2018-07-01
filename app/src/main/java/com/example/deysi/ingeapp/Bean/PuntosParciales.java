/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.deysi.ingeapp.Bean;

import android.content.ContentValues;

import com.example.deysi.ingeapp.BaseDeDatos.Datos;
import com.example.deysi.ingeapp.Bean.interfaces.Guardable;

/**
 *
 * @author Martin
 */
public class PuntosParciales extends Listable implements Guardable{
    private final int id;
    private final Materia materia;
    private int porcentaje;
    private String anho;

    public PuntosParciales(int id, Materia materia, int porcentaje, String anho) {
        this.id = id;
        this.materia = materia;
        this.porcentaje = porcentaje;
        this.anho = anho;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTabla() {
        return "puntosparciales";
    }

    @Override
    public ContentValues getValues() {
        ContentValues v = new ContentValues();
        v.put("idpuntosparciales", getId());
        v.put("fecha", getFecha());
        v.put("puntaje", getPorcentaje());
        v.put("materia", getMateria());
        v.put("alumno_idalumno", Datos.ALUMNO.getId());
        return v;
    }

    @Override
    public String getTitulo() {
        return "PP: "+porcentaje+"%";
    }

    @Override
    public String getFecha() {
        return anho;
    }

    @Override
    public char getTipo() {
        return Listable.PP;
    }

    @Override
    public int getSemestre() {
        return materia.getSemestre();
    }

    public String getMateria() {
        return materia.getTitulo();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
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
        final PuntosParciales other = (PuntosParciales) obj;
        if (this.id != other.id) {
            return false;
        }
        if(!this.anho.equals(other.anho) && !this.materia.equals(other.materia)){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PuntosParciales{" + "id=" + id + ", materia=" + materia + ", porcentaje=" + porcentaje + ", anho=" + anho + '}';
    }
    
    
    
    
}
