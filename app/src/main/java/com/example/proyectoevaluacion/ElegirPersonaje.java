package com.example.proyectoevaluacion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoevaluacion.adaptadores.AdaptadorRecycler;
import com.example.proyectoevaluacion.utils.Personaje;

import java.util.ArrayList;

public class ElegirPersonaje extends AppCompatActivity implements AdaptadorRecycler.OnPersonajeListener, View.OnClickListener {

    private EditText textoFiltrar;
    private Button botonAgregar;

    private ImageView imagenSeleccionada;
    private TextView atributosPersonajeSeleccionado;
    private Button botonJugar;

    private RecyclerView recyclerPersonajes;
    private AdaptadorRecycler adaptadorRecycler;
    private ArrayList<Personaje> listaPersonajes;


    private Personaje personajeSeleccionado = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_personaje);

        instancias();
        configurarRecycler();
        acciones();
    }

    private void configurarRecycler() {
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerPersonajes.setAdapter(adaptadorRecycler);
        adaptadorRecycler.notifyDataSetChanged();
    }

    private void acciones() {
        botonAgregar.setOnClickListener(this);
        botonJugar.setOnClickListener(this);

        /*textoFiltrar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (Personaje item : listaPersonajes) {
                    if (item.getNombre().toLowerCase().equals(s)) {
                        listaPersonajesFiltrada.add(item);
                    }
                }
                adaptadorRecycler.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
    }

    private void instancias() {
        textoFiltrar = findViewById(R.id.edit_text_busqueda);
        botonAgregar = findViewById(R.id.boton_agregar_personaje);

        recyclerPersonajes = findViewById(R.id.recycler_personajes);
        listaPersonajes = new ArrayList<Personaje>();
        adaptadorRecycler = new AdaptadorRecycler(this,listaPersonajes);

        imagenSeleccionada = findViewById(R.id.imagen_personaje_seleccionado);
        atributosPersonajeSeleccionado = findViewById(R.id.atributos_personaje_seleccionado);
        botonJugar = findViewById(R.id.boton_jugar);
    }

    @Override
    public void OnPersonajeSelected(Personaje personaje) {
        imagenSeleccionada.setImageResource(personaje.getImagen());
        atributosPersonajeSeleccionado.setText("HP: " + personaje.getHp() + " AF: " + personaje.getAf() + " DF: "
                + personaje.getDf() + " AM: " + personaje.getAm() + " DM: " + personaje.getDm());

        personajeSeleccionado = personaje;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.boton_agregar_personaje:
                intent = new Intent(getApplicationContext(),CreacionPersonaje.class);
                startActivityForResult(intent,1);

                break;

            case R.id.boton_jugar:
                if (personajeSeleccionado != null) {
                    intent = new Intent(getApplicationContext(),ArenaDeCombate.class);
                    intent.putExtra("personajeSeleccionado",personajeSeleccionado);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Ningun personaje seleccionado",Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1 && data.getExtras().getSerializable("personajeCreado") != null) {
            Personaje personajeNuevo = (Personaje) data.getExtras().getSerializable("personajeCreado");
            listaPersonajes.add(personajeNuevo);
            adaptadorRecycler.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "El personaje no se creo correctamente", Toast.LENGTH_SHORT).show();
        }
    }
}