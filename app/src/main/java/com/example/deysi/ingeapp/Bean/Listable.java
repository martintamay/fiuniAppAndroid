package com.example.deysi.ingeapp.Bean;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Martin on 31/07/2017.
 */

public abstract class Listable implements Comparable {
    /*constantes*/
    public static char SEMESTRE = 'S';
    public static char MATERIA = 'M';
    public static char PP = 'P';
    public static char NOTA = 'N';

    /*métodos*/
    public abstract String getTitulo();
    public String getFecha(){
        return "";
    }
    public abstract char getTipo();

    public abstract int getSemestre();
    public abstract String getMateria();

    @Override
    public int compareTo(@NonNull Object another){
        Listable otro = (Listable)another;
        if (getSemestre()==otro.getSemestre()){
            if (getTipo()==SEMESTRE) return -1;
            if (getMateria().equals(otro.getMateria())){
                if (getTipo()==MATERIA) return -1;
                return -getFecha().compareTo(otro.getFecha());
            }else{
                return -getMateria().compareTo(otro.getMateria());
            }
        }
        return getSemestre()-otro.getSemestre();
    }
}
