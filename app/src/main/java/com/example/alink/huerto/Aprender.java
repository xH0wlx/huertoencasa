package com.example.alink.huerto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alink on 08-07-2016.
 */
public class Aprender implements Parcelable{

    private String nombreSeccion;
    private String detalleSeccion;
    private String nombreTabla;

    public Aprender(String nombreSeccion,String detalleSeccion, String nombreTabla) {
        this.nombreSeccion = nombreSeccion;
        this.nombreTabla = nombreTabla;
        this.detalleSeccion = detalleSeccion;
    }

    protected Aprender(Parcel in) {
        nombreSeccion = in.readString();
        detalleSeccion = in.readString();
        nombreTabla = in.readString();
    }

    public static final Creator<Aprender> CREATOR = new Creator<Aprender>() {
        @Override
        public Aprender createFromParcel(Parcel in) {
            return new Aprender(in);
        }

        @Override
        public Aprender[] newArray(int size) {
            return new Aprender[size];
        }
    };

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getDetalleSeccion() {
        return detalleSeccion;
    }

    public void setDetalleSeccion(String detalleSeccion) {
        this.detalleSeccion = detalleSeccion;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreSeccion);
        dest.writeString(detalleSeccion);
        dest.writeString(nombreTabla);
    }
}
