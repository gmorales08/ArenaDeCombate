package com.example.proyectoevaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.PeriodicSync;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoevaluacion.utils.Arma;
import com.example.proyectoevaluacion.utils.Ataque;
import com.example.proyectoevaluacion.utils.Clase;
import com.example.proyectoevaluacion.utils.Personaje;
import com.example.proyectoevaluacion.utils.Raza;

import java.lang.reflect.Array;

public class ArenaDeCombate extends AppCompatActivity {

    private ImageView imagenJugador, imagenCpu;
    private TextView vidaJugador, vidaCpu, nombreJugador, textoCombate;
    private Button botonIniciar;
    private Personaje personajeJugador, personajeCpu; //personajeJugadorOriginal, personajeCpuOriginal,
    private int turno = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena_de_combate);

        instancias();
        crearPersonajeCpu();
        cargarDatos();
        acciones();
    }



    private void acciones() {
        botonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"TURNO: " + String.valueOf(turno),Toast.LENGTH_SHORT).show();
                combate();
                turno++;
            }
        });
    }

    //aqui agregamos el texto de combate en tiempo real
    private void agregarHistorial(String nombreJugador, Object accionJugador, String nombreCpu, Object accionCpu, int damageJugador, int damageCpu, int vidaJugador, int vidaCpu, String comentarioJugador, String comentarioCpu) {
        textoCombate.setText("======= TURNO " + turno + " =======" + "\n" +
        nombreJugador + " a usado " + accionJugador + "  ,  " + nombreCpu + " a usado " + accionCpu + "\n" +
        nombreJugador + " a realizado " + damageJugador + " puntos de daño , y " + nombreCpu + " a realizado " + damageCpu + " puntos de daño\n" +
        comentarioJugador + "\n" +
        comentarioCpu + "\n" +
        "VIDA JUG: " + vidaJugador + ", VIDA CPU: " + vidaCpu+ "\n"+ "\n" +
        textoCombate.getText());


    }

    //este metodo lo uso para resetear la vida, y los aumentos de atributos al iniciar un combate nuevo por segunda vez
    private void resetearEstadisticas() {
        //personajeJugador = new Personaje(personajeJugadorOriginal.getNombre(),personajeJugadorOriginal.getRaza(),personajeJugadorOriginal.getClase(),personajeJugadorOriginal.getArma());
        //personajeCpu = personajeJugador = new Personaje(personajeCpuOriginal.getNombre(),personajeCpuOriginal.getRaza(),personajeCpuOriginal.getClase(),personajeCpuOriginal.getArma());
        personajeJugador.setHp();
        personajeJugador.setAf();
        personajeJugador.setDf();
        personajeJugador.setAm();
        personajeJugador.setDm();
        personajeJugador.setTipoDeDamage();

        personajeCpu.setHp();
        personajeCpu.setAf();
        personajeCpu.setDf();
        personajeCpu.setAm();
        personajeCpu.setDm();
        personajeCpu.setTipoDeDamage();

        turno = 0;

        botonIniciar.setText("REVANCHA");
    }

    private void determinarAccion(Personaje personaje, Object[] accion) {
        //en la primera posicion de accion va el tipo de accion (ataque, habilidad), y en la segunda posicion va el tipo de ataque o habilidad
        //utilizo Object para meter tanto Ataque como String
        //Object[] accion = new Object[2];

        //primero determinamos si realiza accion o no (80% realiza accion, 20% no)
        int realizar = (int) (Math.random()*10);
        if (realizar == 0 || realizar == 1) {
            accion[0] = "nada";
            accion[1] = "nada";
        } else {
            //si realizamos una accion, esta puede ser ataque (70%) o habilidad (30%)
            int accionAleatoria = (int) (Math.random()*10);
            if (accionAleatoria>=0 && accionAleatoria<=6) {
                accion[0] = "ataque";
                //dentro de ataques tendremos un 50% de realizar un ataque principal, y un 50% de realizar uno secundario
                int tipoAtaque = (int) (Math.random()*2);
                if (tipoAtaque == 0) {
                    accion[1] = personaje.getAtaques().get(0);
                } else {
                    accion[1] = personaje.getAtaques().get(1);
                }
            } else {
                accion[0] = "habilidad";
                //dentro de habilidades tendremos un 50% de realizar una habilidad de raza, y un 50% de realizar una habilidad de clase
                int tipoHabilidad = (int) (Math.random()*2);
                if (tipoHabilidad == 0) {
                    accion[1] = personaje.getHabilidades().get(0);
                } else {
                    accion[1] = personaje.getHabilidades().get(1);
                }
            }
        }
    }

    private int calcularDamage(int damage, Personaje atacante, Personaje defensor) {
        if (atacante.getTipoDeDamage().equals("fisico")) {
            return ((damage * atacante.getAf()) / defensor.getDf());
        } else {
            return ((damage * atacante.getAm()) / defensor.getDm());
        }
    }

    private void combate() {
        boolean terminado = false;
        String ganador = null;
        Object[] accionJugador = new Object[2];
        Object[] accionCpu = new Object[2];

        int damageJugador = 0;
        int damageCpu = 0;
        String comentarioJugador = ""; //aqui pondre la explicacion si realiza alguna accion especial
        String comentarioCpu = "";
        determinarAccion(personajeJugador,accionJugador);
        determinarAccion(personajeCpu,accionCpu);

        //cuando pedimos una revancha se elimina el historial de la anterior batalla
        if (turno == 1) {
            textoCombate.setText("");
            botonIniciar.setText("INICIAR");
        }

        //EJECUTAR LA ACCION DEL JUGADOR
        if (accionJugador[0].equals("nada")) {
            comentarioJugador = personajeJugador.getNombre() + " no ha realizado ninguna accion";
        } else if (accionJugador[0].equals("ataque")) {
            damageJugador = calcularDamage(((Ataque) accionJugador[1]).getPotencia(),personajeJugador,personajeCpu);
        } else if (accionJugador[0].equals("habilidad")) {
            switch (accionJugador[1].toString()) {
                case "habilidad humana":
                case "habilidad ogro":
                case "habilidad de guerrero":
                case "habilidad de mago":
                    damageJugador = calcularDamage(30,personajeJugador,personajeCpu);
                    comentarioJugador = personajeJugador.getNombre() + " ha realizado un poderoso ataque";
                    break;
                case "habilidad elfica":
                    personajeJugador.curar(35);
                    comentarioJugador = personajeJugador.getNombre() + " se ha curado 35Hp";
                    break;
                case "habilidad enana":
                    if (personajeJugador.getTipoDeDamage().equals("fisico")) {
                        personajeJugador.buff("af",5);
                        comentarioJugador = personajeJugador.getNombre() + " se ha aumentado 5 puntos el af";
                    } else {
                        personajeJugador.buff("am",5);
                        comentarioJugador = personajeJugador.getNombre() + " se ha aumentado 5 puntos el am";
                    }
                    break;
                case "habilidad de clerigo":
                    personajeJugador.curar(50);
                    comentarioJugador = personajeJugador.getNombre() + " se ha curado 50Hp";
                    break;
                case "habilidad de druida":
                    if (personajeCpu.getTipoDeDamage().equals("fisico")) {
                        personajeJugador.buff("df",5);
                        comentarioJugador = personajeJugador.getNombre() + " se ha aumentado 5 puntos la df";
                    } else {
                        personajeJugador.buff("dm",5);
                        comentarioJugador = personajeJugador.getNombre() + " se ha aumentado 5 puntos la dm";
                    }
                    break;
            }
        }

        //EJECUTAR ACCION DE LA CPU
        if (accionCpu[0].equals("nada")) {
            comentarioCpu = personajeCpu.getNombre() + " no ha realizado ninguna accion";
        } else if (accionCpu[0].equals("ataque")) {
            damageCpu = calcularDamage(((Ataque) accionCpu[1]).getPotencia(),personajeCpu,personajeJugador);
        } else if (accionCpu[0].equals("habilidad")) {
            switch (accionCpu[1].toString()) {
                case "habilidad humana":
                case "habilidad ogro":
                case "habilidad de guerrero":
                case "habilidad de mago":
                    damageCpu = calcularDamage(30,personajeCpu,personajeJugador);
                    comentarioCpu = personajeCpu.getNombre() + " ha realizado un poderoso ataque";
                    break;
                case "habilidad elfica":
                    personajeCpu.curar(35);
                    comentarioCpu = personajeCpu.getNombre() + " se ha curado 35Hp";
                    break;
                case "habilidad enana":
                    if (personajeCpu.getTipoDeDamage().equals("fisico")) {
                        personajeCpu.buff("af",5);
                        comentarioCpu = personajeCpu.getNombre() + " se ha aumentado 5 puntos el af";
                    } else {
                        personajeCpu.buff("am",5);
                        comentarioCpu = personajeCpu.getNombre() + " se ha aumentado 5 puntos el am";
                    }
                    break;
                case "habilidad de clerigo":
                    personajeCpu.curar(50);
                    comentarioCpu = personajeCpu.getNombre() + " se ha curado 50Hp";
                    break;
                case "habilidad de druida":
                    if (personajeJugador.getTipoDeDamage().equals("fisico")) {
                        personajeCpu.buff("df",5);
                        comentarioCpu = personajeCpu.getNombre() + " se ha aumentado 5 puntos la df";
                    } else {
                        personajeCpu.buff("dm",5);
                        comentarioCpu = personajeCpu.getNombre() + " se ha aumentado 5 puntos la dm";
                    }
                    break;
            }
        }

        personajeJugador.restarVida(damageCpu);
        personajeCpu.restarVida(damageJugador);

        vidaJugador.setText(String.valueOf(personajeJugador.getHp()));
        vidaCpu.setText(String.valueOf(personajeCpu.getHp()));



        System.out.println(personajeJugador.getNombre() + " a usado " + accionJugador[1] + "  ,  " + personajeCpu.getNombre() + " a usado " + accionCpu[1] + "\n");
        System.out.println(personajeJugador.getNombre() + " a realizado " + damageJugador + " puntos de daño , y " + personajeCpu.getNombre() + " a realizado " + damageCpu + " puntos de daño\n");
        System.out.println(comentarioJugador + "\n");
        System.out.println(comentarioCpu + "\n" + "\n");
        System.out.println("VIDA CPU" + personajeCpu.getHp() + ", VIDA JUG: " + personajeJugador.getHp());

        agregarHistorial(personajeJugador.getNombre(), accionJugador[1], personajeCpu.getNombre(), accionCpu[1], damageJugador, damageCpu, personajeJugador.getHp(), personajeCpu.getHp(), comentarioJugador, comentarioCpu);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //COMPROBAMOS SI HAY UN GANADOR
        if (personajeJugador.getHp()<=0 || personajeCpu.getHp()<=0) {
            if (personajeJugador.getHp()<=0 && personajeCpu.getHp()<=0) {
                //si hay un empate se echa a suertes
                int suerte = (int)(Math.random()*2);
                if (suerte == 0) {
                    ganador = personajeJugador.getNombre();
                } else {
                    ganador = personajeCpu.getNombre();
                }
            } else if (personajeJugador.getHp()<=0) {
                ganador = personajeCpu.getNombre();
            } else if (personajeCpu.getHp()<=0) {
                ganador = personajeJugador.getNombre();
            }

            textoCombate.setText("EL GANADOR ES " + ganador + ", GRACIAS POR JUGAR" + "\n" + textoCombate.getText());

            Toast.makeText(getApplicationContext(),"El ganador es: "+ganador,Toast.LENGTH_SHORT).show();
            resetearEstadisticas();

        }
    }

    private void cargarDatos() {
        imagenJugador.setImageResource(personajeJugador.getImagen());
        imagenCpu.setImageResource(personajeCpu.getImagen());
        nombreJugador.setText(personajeJugador.getNombre());
    }

    private void crearPersonajeCpu() {
        Raza razaCpu = Raza.values()[(int) (Math.random()*4)];
        Clase claseCpu = Clase.values()[(int) (Math.random()*4)];
        Arma armaCpu = Arma.values()[(int) (Math.random()*4)];

        personajeCpu = new Personaje("CPU",razaCpu,claseCpu,armaCpu);
        //personajeCpuOriginal = new Personaje(personajeCpuOriginal.getNombre(),personajeCpuOriginal.getRaza(),personajeCpuOriginal.getClase(),personajeCpuOriginal.getArma());

        /*System.out.println(personajeJugadorOriginal.getAf());
        System.out.println(personajeJugador.getAf());
        System.out.println(personajeCpuOriginal.getAf());
        System.out.println(personajeCpu.getAf());*/
    }

    private void instancias() {
        imagenJugador = findViewById(R.id.imagen_jugador);
        imagenCpu = findViewById(R.id.imagen_cpu);
        nombreJugador = findViewById(R.id.nombre_jugador);
        vidaJugador = findViewById(R.id.vida_jugador);
        vidaCpu = findViewById(R.id.vida_cpu);
        textoCombate = findViewById(R.id.texto_combate);
        botonIniciar = findViewById(R.id.boton_iniciar_combate);

        personajeJugador = (Personaje) getIntent().getExtras().get("personajeSeleccionado");
        //personajeJugadorOriginal = new Personaje(personajeJugadorOriginal.getNombre(),personajeJugadorOriginal.getRaza(),personajeJugadorOriginal.getClase(),personajeJugadorOriginal.getArma());
    }
}