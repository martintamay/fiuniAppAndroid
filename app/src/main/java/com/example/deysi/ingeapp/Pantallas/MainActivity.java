package com.example.deysi.ingeapp.Pantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deysi.ingeapp.BaseDeDatos.BaseDatosManager;
import com.example.deysi.ingeapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static BaseDatosManager db;

    private Button perfil, notas, noticias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (db==null)  db=new BaseDatosManager(this);
        obtenerReferencias();

    }

    private void obtenerReferencias() {
        perfil = (Button) findViewById(R.id.menu_b_perfil);
        notas = (Button) findViewById(R.id.menu_b_notas);
        noticias = (Button) findViewById(R.id.menu_b_noticias);

        perfil.setOnClickListener(this);
        notas.setOnClickListener(this);
        noticias.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (perfil == v){
            startActivity(new Intent(this, PerfilActivity.class));
        }else if (notas == v){
            startActivity(new Intent(this, NotasActivity.class));
        }
    }
}
