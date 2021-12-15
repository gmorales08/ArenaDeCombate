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
import com.example.proyectoevaluacion.utils.Arma;
import com.example.proyectoevaluacion.utils.Clase;
import com.example.proyectoevaluacion.utils.Raza;

import java.util.ArrayList;
import java.util.Arrays;

public class DialogoArma extends DialogFragment {

    private OnDialogoArmaSelected listener;
    private ArrayList<Arma> listaArmas;
    private ArrayAdapter adapter;
    private AdaptadorDialogo adaptadorDialogo;
    private int posicion;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OnDialogoArmaSelected) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        listaArmas = new ArrayList<Arma>(Arrays.asList(Arma.values()));
        adapter = new ArrayAdapter(getContext(), android.R.layout.select_dialog_singlechoice,listaArmas);
        adaptadorDialogo = new AdaptadorDialogo(context,listaArmas);
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
                listener.onArmaSelected(listaArmas.get(posicion));
            }
        });

        return builder.create();
    }

    public interface OnDialogoArmaSelected {
        void onArmaSelected(Arma arma);
    }
}
