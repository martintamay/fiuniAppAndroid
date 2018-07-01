package com.example.deysi.ingeapp.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deysi.ingeapp.Bean.Listable;
import com.example.deysi.ingeapp.R;

import java.util.ArrayList;

/**
 * Created by marti on 29 jul 2017.
 */

public class NotasAdapter extends ArrayAdapter<Listable>{
    public NotasAdapter(Context context, ArrayList<Listable> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Listable elemento = getItem(position);
        switch (elemento.getTipo()){
            case 'S':
            case 'M':
                convertView = inflater.inflate(
                        R.layout.titulo_adapter,
                        parent,
                        false);
                // Referencias UI.
                TextView titulo = (TextView) convertView.findViewById(R.id.titulo_texto);
                // setear
                titulo.setText(elemento.getTitulo());
                if (elemento.getTipo()=='S'){
                    convertView.setBackgroundColor(Color.rgb(112,3,3));
                }else{
                    convertView.setBackgroundColor(Color.GRAY);
                }
                titulo.setTextColor(ColorStateList.valueOf(Color.WHITE));
                break;
            case 'P':
            case 'N':
                convertView = inflater.inflate(
                        R.layout.notas_adapter,
                        parent,
                        false);
                // Referencias UI.
                TextView nota = (TextView) convertView.findViewById(R.id.notas_nota);
                TextView fecha = (TextView) convertView.findViewById(R.id.notas_fecha);
                // setear
                nota.setText(elemento.getTitulo());
                fecha.setText(elemento.getFecha());
                break;
        }
        return convertView;
    }
}
