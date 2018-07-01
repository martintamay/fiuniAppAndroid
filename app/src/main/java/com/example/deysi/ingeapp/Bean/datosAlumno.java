/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.deysi.ingeapp.Bean;

import java.util.ArrayList;

/**
 *
 * @author Deysi
 */
public class datosAlumno {

    public static ArrayList<Materia> MATERIAS;
    public static Alumno ALUMNO;

    /**
     * busca una meteria por su id
     */
    public static Materia getMateria(int id) {
        if (MATERIAS != null) {
            for (int i = 0; i < MATERIAS.size(); i++) {
                if (MATERIAS.get(i).getId() == id) {
                    return MATERIAS.get(i);
                }
            }
        }
        return null;
    }
}
