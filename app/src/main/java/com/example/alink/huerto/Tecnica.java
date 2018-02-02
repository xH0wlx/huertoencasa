package com.example.alink.huerto;

/**
 * Created by Alink on 09-07-2016.
 */
public class Tecnica {

    private String nombre, descripcion;

    public Tecnica(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
