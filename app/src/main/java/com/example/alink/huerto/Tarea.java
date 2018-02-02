package com.example.alink.huerto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alink on 10-07-2016.
 */
public class Tarea implements Parcelable{

    private String nombre;
    private int estado;

    public Tarea(String nombre, int estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    protected Tarea(Parcel in) {
        nombre = in.readString();
        estado = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(estado);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tarea> CREATOR = new Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel in) {
            return new Tarea(in);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
