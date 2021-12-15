package com.example.proyectoevaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ComoJugar extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<String> listaSpinner;
    private ArrayAdapter adaptadorSpinner;

    private TextView textoScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_como_jugar);

        instancias();
        configurarSpinner();
        acciones();
    }

    private void acciones() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listaSpinner.get(position).equals("Personaje")) {
                    textoScroll.setText("Para poder jugar debes crear un personaje.\n\n" +
                            "Para crear un personaje debes elegir una raza, una clase y un arma.\n\n" +
                            "Los atributos de tu personaje varian dependiendo de lo que hayas elegido.\n\n" +
                            "Puedes crear mas de un personaje y luego usar el que prefieras.");
                } else if (listaSpinner.get(position).equals("Asignacion de atributos")) {
                    textoScroll.setText("Hay 5 atributos: \n\n" +
                            "HP: puntos de salud\n\n" +
                            "AF: ataque fisico\n\n" +
                            "DF: defensa fisica\n\n" +
                            "AM: ataque magico\n\n" +
                            "DM: defensa magica\n\n" +
                            "\n\n" +
                            "Los HP son iguales para todos los personajes\n\n" +
                            "Los demas atributos se establecen realizando un lanzamiento de 3 dados como base, a los que se añaden de 0 a 3 dados mas dependiendo de la raza y de 0 a 3 dados mas dependiendo de la clase.\n\n" +
                            "Puedes ver los atributos de tu personaje seleccionandolo en el menu.");
                } else if (listaSpinner.get(position).equals("Combate")) {
                    textoScroll.setText("Una vez tienes creado el personaje puedes acceder a la arena.\n\n" +
                            "En la arena tu personaje luchara contra la CPU.\n\n" +
                            "El personaje de la CPU es creado de manera aleatoria.\n\n" +
                            "El combate se desarrolla por turnos donde se realizan acciones aleatorias hasta que uno de los dos personajes se haya quedado sin puntos de salud, entonces el combate termina.");
                } else if (listaSpinner.get(position).equals("Acciones durante el combate")) {
                    textoScroll.setText("Las acciones realizadas durante el combate son aleatorias.\n\n" +
                            "Las acciones disponibles para cada personaje son: \n\n\n\n" +
                            "70% de realizar un ataque basico:\n\n" +
                            "   - 35% de realizar un ataque principal\n\n" +
                            "   - 35% de realizar un ataque secundario\n\n\n" +
                            "30% de utilizar una habilidad:\n\n" +
                            "   - 15% de utilizar una habilidad de raza\n\n" +
                            "   - 15% de utilizar una habilidad de clase\n\n\n\n" +
                            "Ademas, en cada turno, hay un 20% de no realizar ninguna accion y perder el turno");
                } else if (listaSpinner.get(position).equals("Bonificaciones de raza y clase")) {
                    textoScroll.setText("Los humanos son equilibrados en todos los atributos.\n\n" +
                            "Los elfos se especializan en ataque magico.\n\n" +
                            "Los enanos se especializan en defensa fisica. \n\n" +
                            "Los ogros se especializan en ataque fisico. \n\n\n\n" +
                            "Los guerreros se especializan en ataque fisico. \n\n" +
                            "Los magos se especializan en ataque magico. \n\n" +
                            "Los clerigos se especializan en defensa magica. \n\n" +
                            "Los druidas son equilibrados en todos los atributos");
                } else if (listaSpinner.get(position).equals("Habilidades de raza y clase")) {
                    textoScroll.setText("La habilidad de los humanos consiste en un poderoso ataque.\n\n" +
                            "La habilidad de los elfos consiste en curarse.\n\n" +
                            "La habilidad de los enanos consiste en aumentarse el ataque cuyo atributo sea mas alto, ya sea magico o fisico. \n\n" +
                            "La habilidad de los ogros consiste en un poderoso ataque. \n\n\n\n" +
                            "La habilidad de los guerreros consiste en un poderoso ataque. \n\n" +
                            "La habilidad de los magos consiste en un poderoso ataque. \n\n" +
                            "La habilidad de los clerigos consiste en curarse. \n\n" +
                            "La habilidad de los druidas consiste en aumentarse la defensa cuyo atributo sea mas alto, ya sea magica o fisica.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configurarSpinner() {
        spinner.setAdapter(adaptadorSpinner);
        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaSpinner.add("Personaje");
        listaSpinner.add("Asignacion de atributos");
        listaSpinner.add("Combate");
        listaSpinner.add("Acciones durante el combate");
        listaSpinner.add("Bonificaciones de raza y clase");
        listaSpinner.add("Habilidades de raza y clase");

        adaptadorSpinner.notifyDataSetChanged();
    }

    private void instancias() {
        spinner = findViewById(R.id.spinner);
        listaSpinner = new ArrayList<String>();
        adaptadorSpinner = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,listaSpinner);

        textoScroll = findViewById(R.id.texto_scroll);
    }
}