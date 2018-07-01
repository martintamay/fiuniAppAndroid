package com.example.deysi.ingeapp.BaseDeDatos;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Deysi on 08/09/2017.
 */
public class JSONDownloader extends AsyncTask<URL, Integer , JSONArray> {
    private String enlace, metodo, pars;
    public static final String SERVIDOR="http://localhost/RestFulInge/";
    private URL url;

    public JSONDownloader(String metodo, String enlace, String pars) {
        this.metodo = metodo;
        this.enlace = enlace;
        this.pars = pars;
    }
    public JSONDownloader(String metodo, String enlace, JSONObject pars) {
        this.metodo = metodo;
        this.enlace = enlace;
        this.pars = pars.toString();
    }
    public JSONDownloader(String metodo, String enlace) {
        this.metodo = metodo;
        this.enlace = enlace;
        this.pars = "";
    }
    @Override
    protected void onPreExecute() {
        try {
            url=new URL(enlace);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        super.onPreExecute();
    }


    @Override
    protected JSONArray doInBackground(URL... params) {
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

           String lec;
            String retorno="";
            lec=br.readLine();
            while(lec!= null){
                retorno+=lec;
                lec=br.readLine();
            }
            return new JSONArray(retorno);
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }

        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(JSONArray o) {
        super.onPostExecute(o);
    }



}
