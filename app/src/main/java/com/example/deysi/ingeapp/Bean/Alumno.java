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

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Alumno implements Guardable{
    private final int id;
    private String nombre, ci, correo, contrasenha, fecha_creacion;
    private int ingreso, carrera_idcarrera;
    private final ArrayList<Nota> notas;
    private final ArrayList<PuntosParciales> pp;
    private final ArrayList<Anuncio> anuncios;
    private final ArrayList<Materia> materias;
    
    
    /**
     * Constructor inicial del alumno que recibe su id y su nombre y 
     * inicializa los arraylist
     * @param id
     * @param nombre
     */    
    public Alumno(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
        notas = new ArrayList();
        pp = new ArrayList(); 
        anuncios = new ArrayList();
        materias = new ArrayList();
    }

    public Alumno(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt("idalumno");
        this.nombre = jsonObject.getString("nombres");
        notas = new ArrayList();
        pp = new ArrayList();
        anuncios = new ArrayList();
        materias = new ArrayList();
    }

    public void setMateria(Materia materia){
        if(!materias.contains(materia)){
            materias.add(materia);
        }
    }

    public ArrayList<Materia> getMaterias(){
        return materias;
    }

    public void removerMateria(Materia materia){
        materias.remove(materia);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTabla() {
        return "alumno";
    }

    @Override
    public ContentValues getValues() {
        ContentValues v = new ContentValues();
        v.put("idalumno", getId());
        v.put("nombres", getNombre());
        v.put("ci", ci);
        v.put("correo", correo);
        v.put("contrasenha", contrasenha);
        v.put("ingreso", ingreso);
        v.put("carrera_idcarrera", carrera_idcarrera);
        return v;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public ArrayList<PuntosParciales> getPp() {
        return pp;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre  + ", notas=" + notas + ", pp=" + pp + '}';
    }
    
    public void agregarNota(Nota n) throws Exception{
        if(this.notas.contains(n)){
            throw new Exception("Nota Duplicada");
        }else{
            this.notas.add(n);
        }
    }
    
    public void agregarPp(PuntosParciales p) throws Exception{
        if(this.pp.contains(p)){
            throw new Exception("PP Duplicado");
        }else{
            this.pp.add(p);
        }
    }
    
    public void agregarAnuncio(Anuncio a) throws Exception{
        if(this.anuncios.contains(a)){
            throw new Exception("PP Anuncios");
        }else{
            this.anuncios.add(a);
        }
    }
}
