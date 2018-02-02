package com.example.alink.huerto;

/**
 * Created by Luis on 10-07-2016.
 */
public class PlantaAux {
    String idPlanta;
    int cantidad;
    String fecha;
    String nombre;

    public PlantaAux(String idPlanta, int cantidad, String fecha, String nombre) {
        this.idPlanta = idPlanta;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(String idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
