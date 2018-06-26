/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.deysi.ingeapp.Bean;

/**
 *
 * @author Martin
 */
public class PuntosParciales {
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

    public Materia getMateria() {
        return materia;
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
