package com.example.proyectoevaluacion.utils;

import java.io.Serializable;

public class Ataque implements Serializable {
    private String nombre;
    private int potencia; //pf -> potencia fisica, pm -> potencia magica

    public Ataque(String nombre, int potencia) {
        this.nombre = nombre;
        this.potencia = potencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
