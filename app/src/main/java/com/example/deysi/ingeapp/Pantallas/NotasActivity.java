package com.example.deysi.ingeapp.Pantallas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deysi.ingeapp.Adapters.NotasAdapter;
import com.example.deysi.ingeapp.Bean.Alumno;
import com.example.deysi.ingeapp.Bean.Listable;
import com.example.deysi.ingeapp.Bean.Materia;
import com.example.deysi.ingeapp.Bean.Nota;
import com.example.deysi.ingeapp.Bean.PuntosParciales;
import com.example.deysi.ingeapp.R;

import java.util.ArrayList;
import java.util.Collections;

public class NotasActivity extends AppCompatActivity {
    /*TODO obtener de la base de datos*/
    private static Alumno alumno = new Alumno(1, "Martín", "Tamay");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        setTitle("Notas");
        //todo quitar
        try {
            setearAlumno(alumno);
        } catch (Exception e) {}
        setearReferencias();
    }

    private void setearReferencias() {
        ListView lv = (ListView) findViewById(R.id.notas_lista);
        lv.setAdapter(new NotasAdapter(this, obtenerLista(alumno)));
    }

    private ArrayList<Listable> obtenerLista(Alumno alumno) {
        ArrayList<Listable> lista = new ArrayList();

        ArrayList<Nota> notas = alumno.getNotas();
        ArrayList<PuntosParciales> pps = alumno.getPp();
        ArrayList<Integer> semestres = new ArrayList();
         for (int i=0; i<notas.size(); i++){
            if (!semestres.contains(notas.get(i).getSemestre())){
                semestres.add(notas.get(i).getSemestre());
            }
        }
        for (int i=0; i<pps.size(); i++){
            if (!semestres.contains(pps.get(i).getSemestre())){
                semestres.add(pps.get(i).getSemestre());
            }
        }

        lista.addAll(notas);
        lista.addAll(pps);
        lista.addAll(alumno.getMaterias());
        for (final int sem:semestres){
            lista.add(new Listable() {
                @Override
                public String getTitulo() {
                    return sem+" Semestre";
                }

                @Override
                public char getTipo() {
                    return 'S';
                }

                @Override
                public int getSemestre() {
                    return sem;
                }

                @Override
                public String getMateria() {
                    return "";
                }
            });
        }

        Collections.sort(lista);
        return lista;
    }

    public void setearAlumno(Alumno alumno) throws Exception {
        Materia fisica = new Materia(1, 1, 'I', "Física 1"),
                analisis = new Materia(2, 1, 'I', "Análisis 1");

        alumno.setMateria(fisica);
        alumno.setMateria(analisis);

        alumno.agregarPp(new PuntosParciales(1, fisica, 85, "2016"));
        alumno.agregarNota(new Nota(1, fisica, 1, "06-06-2016", 1));
        alumno.agregarNota(new Nota(2, fisica, 2, "08-07-2016", 2));

        alumno.agregarPp(new PuntosParciales(2, analisis, 51, "2016"));
        alumno.agregarNota(new Nota(3, analisis, 4, "15-06-2016", 1));
    }
}
