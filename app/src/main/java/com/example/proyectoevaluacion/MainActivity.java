package com.example.proyectoevaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imagenEscudo1, imagenEscudo2, imagenEspada1, imagenEspada2;
    private Button botonElegirPersonaje, botonComoJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instancias();
        acciones();
    }

    private void acciones() {
        botonElegirPersonaje.setOnClickListener(this);
        botonComoJugar.setOnClickListener(this);
    }


    private void instancias() {
        imagenEscudo1 = findViewById(R.id.imagen_escudo1);
        imagenEscudo2 = findViewById(R.id.imagen_escudo2);
        imagenEspada1 = findViewById(R.id.imagen_espada1);
        imagenEspada2 = findViewById(R.id.imagen_espada2);
        
        botonElegirPersonaje = findViewById(R.id.boton_elegir_personaje);
        botonComoJugar = findViewById(R.id.boton_como_jugar);
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {
            case R.id.boton_elegir_personaje:
                intent = new Intent(getApplicationContext(),ElegirPersonaje.class);
                startActivity(intent);
                break;
            case R.id.boton_como_jugar:
                intent = new Intent(getApplicationContext(),ComoJugar.class);
                startActivity(intent);
                break;
        }
    }
}