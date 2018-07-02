package com.example.deysi.ingeapp.Pantallas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.deysi.ingeapp.BaseDeDatos.Datos;
import com.example.deysi.ingeapp.Bean.Alumno;
import com.example.deysi.ingeapp.R;

public class PerfilActivity extends AppCompatActivity {
    private TextView nombres, ingreso, ci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Alumno al = Datos.ALUMNO;
        getElements();
        setDatos(al);
    }

    private void getElements(){
        nombres = (TextView) this.findViewById(R.id.perfil_t_nombre);
        ingreso = (TextView) this.findViewById(R.id.perfil_t_anho_ingreso);
        ci = (TextView) this.findViewById(R.id.perfil_t_ci);
    }

    private void setDatos(Alumno al){
        nombres.setText("Nombres: "+al.getNombre());
        ingreso.setText("Año de Ingreso: "+al.getIngreso());
        ci.setText("CI: "+al.getCi());
    }
}
