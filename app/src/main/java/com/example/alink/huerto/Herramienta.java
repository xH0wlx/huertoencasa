package com.example.alink.huerto;

import android.graphics.drawable.Drawable;

/**
 * Created by Alink on 09-07-2016.
 */
public class Herramienta {

    private String tipo,  nombre, descripcion, precio;
    private String imagen;

    public Herramienta(String tipo, String nombre, String descripcion, String precio, String imagen) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
