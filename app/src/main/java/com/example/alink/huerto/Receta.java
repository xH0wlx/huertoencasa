package com.example.alink.huerto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicolas on 10-07-16.
 */
public class Receta implements Parcelable{
    private int idReceta;
    private String nombre, ingredientes, tipo, preparacion, imagen;

    public Receta(int idReceta, String nombre, String ingredientes, String tipo, String preparacion, String imagen) {
        this.idReceta = idReceta;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.tipo = tipo;
        this.preparacion = preparacion;
        this.imagen = imagen;
    }

    protected Receta(Parcel in) {
        idReceta = in.readInt();
        nombre = in.readString();
        ingredientes = in.readString();
        tipo = in.readString();
        preparacion = in.readString();
        imagen = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idReceta);
        dest.writeString(nombre);
        dest.writeString(ingredientes);
        dest.writeString(tipo);
        dest.writeString(preparacion);
        dest.writeString(imagen);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Receta> CREATOR = new Creator<Receta>() {
        @Override
        public Receta createFromParcel(Parcel in) {
            return new Receta(in);
        }

        @Override
        public Receta[] newArray(int size) {
            return new Receta[size];
        }
    };

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
