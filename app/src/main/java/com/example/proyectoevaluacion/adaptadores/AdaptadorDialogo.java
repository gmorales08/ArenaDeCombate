package com.example.proyectoevaluacion.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoevaluacion.R;

import java.util.ArrayList;

public class AdaptadorDialogo extends BaseAdapter {

    Context context;
    ArrayList listaDatos;

    public AdaptadorDialogo(Context context, ArrayList listaDatos) {
        this.context = context;
        this.listaDatos = listaDatos;
    }

    @Override
    public int getCount() {
        return listaDatos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_dialogo,parent,false);

        TextView textView = convertView.findViewById(R.id.fila_opcion_dialogo);

        Object opcion = listaDatos.get(position); //uso Object porque lo voy a aplicar a varias clases

        textView.setText(opcion.toString());

        return convertView;
    }
}
