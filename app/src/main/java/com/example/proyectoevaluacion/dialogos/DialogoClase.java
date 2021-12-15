package com.example.proyectoevaluacion.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.proyectoevaluacion.adaptadores.AdaptadorDialogo;
import com.example.proyectoevaluacion.utils.Clase;
import com.example.proyectoevaluacion.utils.Raza;

import java.util.ArrayList;
import java.util.Arrays;

public class DialogoClase extends DialogFragment {

    private OnDialogoClaseSelected listener;
    private ArrayList<Clase> listaClases;
    private ArrayAdapter adapter;
    private AdaptadorDialogo adaptadorDialogo;
    private int posicion;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OnDialogoClaseSelected) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        listaClases = new ArrayList<Clase>(Arrays.asList(Clase.values()));
        adapter = new ArrayAdapter(getContext(), android.R.layout.select_dialog_singlechoice,listaClases);
        adaptadorDialogo = new AdaptadorDialogo(context,listaClases);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Creacion de personaje");

        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                posicion = which;
            }
        });

        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClaseSelected(listaClases.get(posicion));
            }
        });

        return builder.create();
    }

    public interface OnDialogoClaseSelected {
        void onClaseSelected(Clase clase);
    }
}
