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
    private String nombre, ci, correo, contrasenha, fecha_creacion, carrera;
    private int ingreso;
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

    public Alumno(int id, String nombres, String ci, String correo, int ingreso, String carrera){
        this(id, nombres);
        this.ci = ci;
        this.correo = correo;
        this.ingreso = ingreso;
        this.carrera = carrera;
    }

    public Alumno(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt("id");
        this.ingreso = jsonObject.getInt("entry_year");
        this.carrera = jsonObject.getJSONObject("career").getString("description");
        JSONObject per = jsonObject.getJSONObject("person");
        this.nombre = per.getString("names");
        this.correo = per.getString("email");
        this.ci = per.getString("ci");
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

    public int getIngreso(){
        return ingreso;
    }
    public String getCi() {
        return ci;
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
        v.put("ingreso", ingreso);
        v.put("carrera", carrera);
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
    
    public void agregarNota(Nota n){
        if(this.notas.contains(n)) {
            notas.remove(n);
        }
        this.notas.add(n);
    }
    
    public void agregarPp(PuntosParciales p){
        if(this.pp.contains(p)){
            pp.remove(p);
        }
        this.pp.add(p);
    }

    public void agregarMateria(Materia m) {
        if(this.materias.contains(m)){
            materias.remove(m);
        }
        materias.add(m);
    }
    
    public void agregarAnuncio(Anuncio a) throws Exception{
        if(this.anuncios.contains(a)){
            throw new Exception("PP Anuncios");
        }else{
            this.anuncios.add(a);
        }
    }

    public Materia getMateria(int idmateria) {
        int index = materias.indexOf(new Materia(idmateria));
        if (index >= 0){
            return materias.get(index);
        }
        return null;
    }

    public String getCorreo() {
        return correo;
    }
}
