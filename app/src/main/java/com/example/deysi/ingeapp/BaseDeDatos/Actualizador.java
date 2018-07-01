package com.example.deysi.ingeapp.BaseDeDatos;

import android.util.Log;

import com.example.deysi.ingeapp.Bean.Alumno;
import com.example.deysi.ingeapp.Bean.Nota;
import com.example.deysi.ingeapp.Pantallas.MainActivity;

import org.json.*;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Deysi on 08/09/2017.
 */
public class Actualizador {

    public static void loginAlumno() throws JSONException {
        RestClient.put("students/log_in", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(response.length()==0){
                    //usuario o contraseña incorrectos
                }else{
                    try {
                        Alumno al = new Alumno(response);
                        MainActivity.db.guardar(al);
                        Datos.ALUMNO = al;
                    } catch (JSONException e) {
                        //error con el servidor
                        Log.e("Actualizador", "Error con el servidor");
                    }
                }
            }
        });
    }


    public static void getNotas(final Alumno al) throws JSONException {
        RestClient.put("students/"+al.getId()+"/notes", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject jnota = (JSONObject) response.get(i);
                        Nota nota = new Nota(jnota);
                        MainActivity.db.guardar(nota);
                        al.agregarNota(nota);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public static void logearAlumno2(String usuario, String contrasenha) throws JSONException {
        JSONObject jo= new JSONObject(), student = new JSONObject();
        student.put("email", usuario);
        student.put("password", contrasenha);
        jo.put("student", student);
        JSONDownloader jd= new JSONDownloader("Alumnos","GET",jo){
            @Override
            protected void onPostExecute(JSONArray o) {
               for(int i=0; i<o.length();i++ ){
                   try {
                       MainActivity.db.guardar(new Alumno(o.getJSONObject(i)));
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
            }
        };
    }
}
