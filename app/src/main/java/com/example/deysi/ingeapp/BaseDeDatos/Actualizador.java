package com.example.deysi.ingeapp.BaseDeDatos;

import android.util.Log;

import com.example.deysi.ingeapp.Bean.Alumno;
import com.example.deysi.ingeapp.Bean.Materia;
import com.example.deysi.ingeapp.Bean.Nota;
import com.example.deysi.ingeapp.Bean.PuntosParciales;
import com.example.deysi.ingeapp.Pantallas.MainActivity;

import org.json.*;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Deysi on 08/09/2017.
 */
public class Actualizador {

    public static void loginAlumno(String correo, String contrasenha, final BaseDatosManager bd, final ActionManager action) {
        RequestParams pars = new RequestParams();
        pars.add("student[email]", correo);
        pars.add("student[password]", contrasenha);
        RestClient.put("students/log_in", pars, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode==401){
                    Log.e("LOGIN ACTION", "Usuario o contraseña incorrectos");
                    Datos.ALUMNO = null;
                    action.onFail();
                    //usuario o contraseña incorrectosnse.length
                }else{
                    try {
                        Alumno al = new Alumno(response);
                        bd.guardar(al);
                        Datos.ALUMNO = al;
                        action.onExecute((Boolean)true);
                    } catch (JSONException e) {
                        action.onExecute((Boolean)false);
                        Log.e("Actualizador", "Error al leer el json: "+response.toString());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                action.onFail(errorResponse);
                Log.e("Actualizador", errorResponse.toString());
            }
        });
    }

    public static void getMaterias(final Alumno al, final ActionManager action) {
        RestClient.get("students/"+al.getId()+"/subjects", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject jmateria = response.getJSONObject(i);
                        Materia materia = new Materia(jmateria);
                        MainActivity.db.guardar(materia);
                        if(Datos.ALUMNO!=null) Datos.ALUMNO.agregarMateria(materia);
                        action.onExecute(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                action.onFail(errorResponse);
            }
        });
    }

    public static void getNotas(final Alumno al, final ActionManager action) {
        RestClient.get("students/"+al.getId()+"/notes", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject jnota = response.getJSONObject(i);
                        if (jnota.getString("noteType").equals("FINAL")){
                            Nota nota = new Nota(jnota);
                            MainActivity.db.guardar(nota);
                            al.agregarNota(nota);
                        }else if (jnota.getString("noteType").equals("PP")){
                            PuntosParciales pp = new PuntosParciales(jnota);
                            MainActivity.db.guardar(pp);
                            al.agregarPp(pp);
                        }
                        action.onExecute(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                action.onFail(errorResponse);
            }
        });
    }

    /*
    *   SOLICITUDES A SQLITE
    */
    public static void getAlumno(BaseDatosManager db){
        Datos.ALUMNO = db.getAlumno();
    }
    public static void getMaterias(BaseDatosManager db){
        db.getMaterias();
    }
    public static void getNotas(BaseDatosManager db){
        db.getNotas();
    }
}
