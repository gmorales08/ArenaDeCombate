package com.example.proyectoevaluacion.utils;

import android.widget.ImageView;

import com.example.proyectoevaluacion.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Personaje implements Serializable {
    private String nombre, tipoDeDamage; //tipo de damage indica si el personaje inflinge daño magico o fisico
    private int hp, af, df, am, dm; //(hp -> puntos de vida, af -> ataque fisico, df -> defensa fisica, am -> ataque magico, dm -> defensa magica
    private Raza raza;
    private Clase clase;
    private Arma arma;
    private int imagen;

    private ArrayList<Ataque> ataques = new ArrayList<Ataque>();
    private ArrayList<String> habilidades = new ArrayList<String>(); //las habilidades son String, ya que la funcionalidad se aplica en el combate

    public Personaje(String nombre, Raza raza, Clase clase, Arma arma) {
        this.nombre = nombre;
        this.raza = raza;
        this.clase = clase;
        this.arma = arma;
        setHp();
        setAf();
        setDf();
        setAm();
        setDm();
        setAtaques();
        setHabilidades();
        setImagen();
        setTipoDeDamage();
    }

    public void buff(String atributo, int cantidad) {
        if (atributo.equals("af")) {
            this.af = af + cantidad;
        } else if (atributo.equals("am")) {
            this.am = am + cantidad;
        } else if (atributo.equals("df")) {
            this.df = df + cantidad;
        } else if (atributo.equals("dm")) {
            this.dm = dm + cantidad;
        }
    }

    public void deBuff(String atributo, int cantidad) {
        if (atributo.equals("af")) {
            this.af = af - cantidad;
        } else if (atributo.equals("am")) {
            this.am = am - cantidad;
        } else if (atributo.equals("df")) {
            this.df = df - cantidad;
        } else if (atributo.equals("dm")) {
            this.dm = dm - cantidad;
        }
    }

    public void curar(int cantidad) {
        this.hp = getHp() + cantidad;
    }

    public void restarVida(int damage) {
        this.hp = getHp() - damage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHp() {
        return hp;
    }

    public void setHp() {
        this.hp = 300; //todos los personajes tienen 1000HP
    }

    public int getAf() {
        return af;
    }

    public void setAf() {
        af = 0;
        for (int i = 0; i < 4; i++) {
            int tirada = (int)(Math.random()*6 + 1);
            af += tirada;
        }

        if (getRaza().equals(Raza.HUMANO)) {
            int tirada = (int)(Math.random()*6 + 1);
            af += tirada;
        } else if (getRaza().equals(Raza.ELFO)) {

        } else if (getRaza().equals(Raza.ENANO)) {
            int tirada = (int)(Math.random()*6 + 1);
            af += tirada;
        } else if (getRaza().equals(Raza.OGRO)) {
            for (int i = 0; i < 2; i++) {
                int tirada = (int)(Math.random()*6 + 1);
                af += tirada;
            }
        }

        if (getClase().equals(Clase.GUERRERO)) {
            for (int i = 0; i < 3; i++) {
                int tirada = (int)(Math.random()*6 + 1);
                af += tirada;
            }
        } else if (getClase().equals(Clase.MAGO)) {

        } else if (getClase().equals(Clase.CLERIGO)) {

        } else if (getClase().equals(Clase.DRUIDA)) {
            int tirada = (int)(Math.random()*6 + 1);
            af += tirada;
        }
    }

    public int getDf() {
        return df;
    }

    public void setDf() {
        df = 0;
        for (int i = 0; i < 3; i++) {
            int tirada = (int)(Math.random()*6 + 1);
            df += tirada;
        }
        if (getRaza().equals(Raza.HUMANO)) {
            int tirada = (int)(Math.random()*6 + 1);
            df += tirada;
        } else if (getRaza().equals(Raza.ELFO)) {

        } else if (getRaza().equals(Raza.ENANO)) {
            for (int i = 0; i < 2; i++) {
                int tirada = (int)(Math.random()*6 + 1);
                df += tirada;
            }
        } else if (getRaza().equals(Raza.OGRO)) {
            int tirada = (int)(Math.random()*6 + 1);
            df += tirada;
        }

        if (getClase().equals(Clase.GUERRERO)) {
            int tirada = (int)(Math.random()*6 + 1);
            df += tirada;
        } else if (getClase().equals(Clase.MAGO)) {
            int tirada = (int)(Math.random()*6 + 1);
            df += tirada;
        } else if (getClase().equals(Clase.CLERIGO)) {
            int tirada = (int)(Math.random()*6 + 1);
            df += tirada;
        } else if (getClase().equals(Clase.DRUIDA)) {
            int tirada = (int)(Math.random()*6 + 1);
            df += tirada;
        }
    }

    public int getAm() {
        return am;
    }

    public void setAm() {
        am = 0;
        for (int i = 0; i < 3; i++) {
            int tirada = (int)(Math.random()*6 + 1);
            am += tirada;
        }
        if (getRaza().equals(Raza.HUMANO)) {
            int tirada = (int)(Math.random()*6 + 1);
            am += tirada;
        } else if (getRaza().equals(Raza.ELFO)) {
            for (int i = 0; i < 3; i++) {
                int tirada = (int)(Math.random()*6 + 1);
                am += tirada;
            }
        } else if (getRaza().equals(Raza.ENANO)) {

        } else if (getRaza().equals(Raza.OGRO)) {

        }

        if (getClase().equals(Clase.GUERRERO)) {

        } else if (getClase().equals(Clase.MAGO)) {
            for (int i = 0; i < 2; i++) {
                int tirada = (int)(Math.random()*6 + 1);
                am += tirada;
            }
        } else if (getClase().equals(Clase.CLERIGO)) {
            int tirada = (int)(Math.random()*6 + 1);
            am += tirada;
        } else if (getClase().equals(Clase.DRUIDA)) {
            int tirada = (int)(Math.random()*6 + 1);
            am += tirada;
        }
    }

    public int getDm() {
        return dm;
    }

    public void setDm() {
        dm = 0;
        for (int i = 0; i < 3; i++) {
            int tirada = (int)(Math.random()*6 + 1);
            dm += tirada;
        }
        if (getRaza().equals(Raza.HUMANO)) {
            int tirada = (int)(Math.random()*6 + 1);
            dm += tirada;
        } else if (getRaza().equals(Raza.ELFO)) {
            int tirada = (int)(Math.random()*6 + 1);
            dm += tirada;
        } else if (getRaza().equals(Raza.ENANO)) {
            int tirada = (int)(Math.random()*6 + 1);
            dm += tirada;
        } else if (getRaza().equals(Raza.OGRO)) {
            int tirada = (int)(Math.random()*6 + 1);
            dm += tirada;
        }

        if (getClase().equals(Clase.GUERRERO)) {

        } else if (getClase().equals(Clase.MAGO)) {
            int tirada = (int)(Math.random()*6 + 1);
            dm += tirada;
        } else if (getClase().equals(Clase.CLERIGO)) {
            for (int i = 0; i < 2; i++) {
                int tirada = (int)(Math.random()*6 + 1);
                dm += tirada;
            }
        } else if (getClase().equals(Clase.DRUIDA)) {
            int tirada = (int)(Math.random()*6 + 1);
            dm += tirada;
        }
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public ArrayList<Ataque> getAtaques() {
        return ataques;
    }

    public void setAtaques() {
        if (getArma().equals(Arma.ESPADA)) {
            ataques.add(new Ataque("Tajo",16));
            ataques.add(new Ataque("Estocada",20));
        } else if (getArma().equals(Arma.HACHA)) {
            ataques.add(new Ataque("Golpe",12));
            ataques.add(new Ataque("Corte",24));
        } else if (getArma().equals(Arma.ARCO)) {
            ataques.add(new Ataque("Flecha",16));
            ataques.add(new Ataque("Arpon",20));
        } else if (getArma().equals(Arma.BACULO)) {
            ataques.add(new Ataque("Bola de fuego",12));
            ataques.add(new Ataque("Rayo",24));
        }
    }

    public ArrayList<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades() {
        if (getRaza().equals(Raza.HUMANO)) {
            habilidades.add("habilidad humana");
        } else if (getRaza().equals(Raza.ELFO)) {
            habilidades.add("habilidad elfica");
        } else if (getRaza().equals(Raza.ENANO)) {
            habilidades.add("habilidad enana");
        } else if (getRaza().equals(Raza.OGRO)) {
            habilidades.add("habilidad ogro");
        }

        if (getClase().equals(Clase.GUERRERO)) {
            habilidades.add("habilidad de guerrero");
        } else if (getClase().equals(Clase.MAGO)) {
            habilidades.add("habilidad de mago");
        } else if (getClase().equals(Clase.CLERIGO)) {
            habilidades.add("habilidad de clerigo");
        } else if (getClase().equals(Clase.DRUIDA)) {
            habilidades.add("habilidad de druida");
        }
    }

    public void setImagen() {
        if (getRaza().equals(Raza.HUMANO)) {
            imagen = R.drawable.human_face;
        } else if (getRaza().equals(Raza.ELFO)) {
            imagen = R.drawable.elf_face;
        } else if (getRaza().equals(Raza.ENANO)) {
            imagen = R.drawable.dwarf;
        } else if (getRaza().equals(Raza.OGRO)) {
            imagen = R.drawable.orc_face;
        }
    }

    public int getImagen() {
        return imagen;
    }

    public String getTipoDeDamage() {
        return tipoDeDamage;
    }

    public void setTipoDeDamage() {
        if (getAf() >= getAm()) {
            tipoDeDamage = "fisico";
        } else {
            tipoDeDamage = "magico";
        }
    }
}