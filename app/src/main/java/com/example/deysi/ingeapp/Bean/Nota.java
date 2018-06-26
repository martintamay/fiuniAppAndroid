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
public class Nota {
    private final int id, mesa;
    private final Materia materia;
    private int nota;
    private String fecha;

    public Nota(int id, Materia materia, int nota, String fecha, int mesa) {
        this.id = id;
        this.materia = materia;
        this.nota = nota;
        this.fecha = fecha;
        this.mesa = mesa;
    }

    public int getMesa() {
        return mesa;
    }
    
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        hash = 47 * hash + this.id;
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
        final Nota other = (Nota) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!this.fecha.equals(other.getFecha()) && !this.materia.equals(other.getMateria())){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nota{" + "id=" + id + ", materia=" + materia + ", nota=" + nota + ", fecha=" + fecha + '}';
    }
    
}
