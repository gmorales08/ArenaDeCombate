package com.example.proyectoevaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoevaluacion.dialogos.DialogoArma;
import com.example.proyectoevaluacion.dialogos.DialogoClase;
import com.example.proyectoevaluacion.dialogos.DialogoRaza;
import com.example.proyectoevaluacion.utils.Arma;
import com.example.proyectoevaluacion.utils.Clase;
import com.example.proyectoevaluacion.utils.Personaje;
import com.example.proyectoevaluacion.utils.Raza;

public class CreacionPersonaje extends AppCompatActivity implements View.OnClickListener, DialogoRaza.OnDialogoRazaSelected, DialogoClase.OnDialogoClaseSelected, DialogoArma.OnDialogoArmaSelected {

    private Button botonRaza, botonClase, botonArma, botonCrear;
    private EditText nombrePersonaje;
    private Raza raza = null;
    private Clase clase = null;
    private Arma arma = null;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_personaje);

        instancias();
        acciones();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_creacion_personaje, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_opcion_personaje_aleatorio:
                nombrePersonaje.setText("Aleatorio");
                raza = Raza.values()[(int) (Math.random()*4)];
                botonRaza.setText(raza.toString());
                clase = Clase.values()[(int) (Math.random()*4)];
                botonClase.setText(clase.toString());
                arma = Arma.values()[(int) (Math.random()*4)];
                botonArma.setText(arma.toString());
                break;
            case R.id.menu_restablecer_campos:
                nombrePersonaje.setText("");
                botonRaza.setText("RAZA");
                raza = null;
                botonClase.setText("CLASE");
                clase = null;
                botonArma.setText("ARMA");
                arma = null;
                break;
            case R.id.menu_cancelar_creacion:
                startActivity(intent);
                break;
        }

        return true;
    }

    private void acciones() {
        botonRaza.setOnClickListener(this);
        botonClase.setOnClickListener(this);
        botonArma.setOnClickListener(this);
        botonCrear.setOnClickListener(this);
    }

    private void instancias() {
        botonRaza = findViewById(R.id.boton_elegir_raza);
        botonClase = findViewById(R.id.boton_elegir_clase);
        botonArma = findViewById(R.id.boton_elegir_arma);
        botonCrear = findViewById(R.id.boton_crear_personaje);
        nombrePersonaje = findViewById(R.id.texto_nombre_crear);

        intent = new Intent(getApplicationContext(),ElegirPersonaje.class); //lo inicializo aqui por si el usuario da al boton "hacia atras" sin crear el personaje
        setResult(0,intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_elegir_raza:
                DialogoRaza dialogoRaza = new DialogoRaza();
                dialogoRaza.show(getSupportFragmentManager(),null);

                break;
            case R.id.boton_elegir_clase:
                DialogoClase dialogoClase = new DialogoClase();
                dialogoClase.show(getSupportFragmentManager(),null);

                break;
            case R.id.boton_elegir_arma:
                DialogoArma dialogoArma = new DialogoArma();
                dialogoArma.show(getSupportFragmentManager(),null);

                break;
            case R.id.boton_crear_personaje:
                if (nombrePersonaje.getText().toString().isEmpty() || raza == null || clase == null || arma == null) {
                    Toast.makeText(getApplicationContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Personaje personaje = new Personaje(nombrePersonaje.getText().toString(),raza,clase,arma);

                    intent.putExtra("personajeCreado",personaje);
                    setResult(1,intent);
                    finish();
                }

                break;
        }
    }

    @Override
    public void onRazaSelected(Raza raza) {
        botonRaza.setText(raza.toString());
        this.raza = raza;
    }

    @Override
    public void onClaseSelected(Clase clase) {
        botonClase.setText(clase.toString());
        this.clase = clase;
    }

    @Override
    public void onArmaSelected(Arma arma) {
        botonArma.setText(arma.toString());
        this.arma = arma;
    }
}