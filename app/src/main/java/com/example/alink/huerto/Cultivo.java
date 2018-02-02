package com.example.alink.huerto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicolas on 07-07-16.
 */
public class Cultivo implements Parcelable{

    private double largo, ancho, profundidad;
    private String tipoSuelo;
    private int idCultivo;

    public Cultivo(double largo, double ancho, double profundidad, String tipoSuelo, int idCultivo) {
        this.largo = largo;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.tipoSuelo = tipoSuelo;
        this.idCultivo = idCultivo;
    }


    protected Cultivo(Parcel in) {
        largo = in.readDouble();
        ancho = in.readDouble();
        profundidad = in.readDouble();
        tipoSuelo = in.readString();
        idCultivo = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(largo);
        dest.writeDouble(ancho);
        dest.writeDouble(profundidad);
        dest.writeString(tipoSuelo);
        dest.writeInt(idCultivo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cultivo> CREATOR = new Creator<Cultivo>() {
        @Override
        public Cultivo createFromParcel(Parcel in) {
            return new Cultivo(in);
        }

        @Override
        public Cultivo[] newArray(int size) {
            return new Cultivo[size];
        }
    };

    public int getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(int idCultivo) {
        this.idCultivo = idCultivo;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }
}
