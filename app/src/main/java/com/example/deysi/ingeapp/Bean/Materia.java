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
public class Materia {
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