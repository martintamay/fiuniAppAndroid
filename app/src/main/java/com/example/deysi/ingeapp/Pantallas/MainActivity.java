package com.example.deysi.ingeapp.Pantallas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deysi.ingeapp.BaseDeDatos.ActionManager;
import com.example.deysi.ingeapp.BaseDeDatos.Actualizador;
import com.example.deysi.ingeapp.BaseDeDatos.BaseDatosManager;
import com.example.deysi.ingeapp.BaseDeDatos.Datos;
import com.example.deysi.ingeapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static BaseDatosManager db;

    private Button perfil, notas, noticias;
    private TextView infoTitle;
    private ProgressBar progressBar;

    private int cargados = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (db==null)  db=new BaseDatosManager(this);
        obtenerReferencias();

        cargarDatos();
    }

    private void obtenerReferencias() {
        perfil = (Button) findViewById(R.id.menu_b_perfil);
        notas = (Button) findViewById(R.id.menu_b_notas);
        noticias = (Button) findViewById(R.id.menu_b_noticias);
        infoTitle = (TextView) findViewById(R.id.menu_info_tittle);
        progressBar = (ProgressBar) findViewById(R.id.menu_progress_bar);

        perfil.setOnClickListener(this);
        notas.setOnClickListener(this);
        noticias.setOnClickListener(this);
    }

    private void cargarDatos(){
        final ActionManager action = new ActionManager(){
            @Override
            public void onExecute() {
                MainActivity.this.cargaTerminada();
            }

            @Override
            public void onFail(JSONObject o) {
                Log.i("MAIN", "Notas Cargadas");
                if (o!=null) Log.e("MAIN", o.toString());
                else Log.e("MAIN", "Error al cargar sin json");
                MainActivity.this.cargaTerminada();
            }
        };
        Actualizador.getMaterias(Datos.ALUMNO, new ActionManager(){
            @Override
            public void onExecute() {
                Log.i("MAIN", "Materias Cargadas");
                MainActivity.this.cargaTerminada();
                Actualizador.getNotas(Datos.ALUMNO, action);
            }

            @Override
            public void onFail(JSONObject o) {
                if (o!=null) Log.e("MAIN", o.toString());
                else Log.e("MAIN", "Error al cargar sin json");
                MainActivity.this.cargaTerminada();
            }
        });
        //Actualizador.getNotas(Datos.ALUMNO, action);
        /*(JSONException e) {
            Toast toast = Toast.makeText(
                    MainActivity.this,
                    "Error al procesar la respuesta del servidor",
                    Toast.LENGTH_SHORT
            );
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            Log.e("MAIN", "Error al procesar algún json: "+e);
        }*/
    }

    public void cargaTerminada(){
        this.cargados++;
        if (cargados==2){
            new AsyncTask(){

                @Override
                protected Object doInBackground(Object[] objects) {
                    Actualizador.getAlumno(db);
                    Actualizador.getMaterias(db);
                    Actualizador.getNotas(db);
                    return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    //se desactiva la carga
                    infoTitle.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);

                    //se activan los botones
                    perfil.setVisibility(View.VISIBLE);
                    notas.setVisibility(View.VISIBLE);
                    //this.noticias.setVisibility(View.VISIBLE);
                }
            }.execute();
        }
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
