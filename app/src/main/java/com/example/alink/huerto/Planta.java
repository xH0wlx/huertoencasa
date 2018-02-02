package com.example.alink.huerto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Luis on 07-07-2016.
 */
public class Planta implements Parcelable {
    String idPlanta;
    String nombre;
    String nombreCientifico;
    String clase;
    String cuandoPlantar;
    String diasCosecha;
    double distanciaPlantas;
    double distanciaOtrasPlantas;
    double profundidadNecesaria;
    double volumenNecesario;
    String nAbono;
    String nRiego;
    String nSol;
    String tipoSuelo;
    String nTemperatura;
    String urlimagen;

    /*public Planta(String idPlanta, String nombreCientifico, String nombre, String clase,
                  double distanciaPlantas, double distanciaOtrasPlantas, double profundidadNecesaria,
                  double volumenNecesario, String nAbono, String nSol, String nRiego, String tipoSuelo, String nTemperatura) {
        this.idPlanta = idPlanta;
        this.nombreCientifico = nombreCientifico;
        this.nombre = nombre;
        this.clase = clase;
        this.distanciaPlantas = distanciaPlantas;
        this.distanciaOtrasPlantas = distanciaOtrasPlantas;
        this.profundidadNecesaria = profundidadNecesaria;
        this.volumenNecesario = volumenNecesario;
        this.nAbono = nAbono;
        this.nSol = nSol;
        this.nRiego = nRiego;
        this.tipoSuelo = tipoSuelo;
        this.nTemperatura = nTemperatura;
    }*/


    public Planta(){

    }

    protected Planta(Parcel in) {
        idPlanta = in.readString();
        nombre = in.readString();
        nombreCientifico = in.readString();
        clase = in.readString();
        cuandoPlantar = in.readString();
        diasCosecha = in.readString();
        distanciaPlantas = in.readDouble();
        distanciaOtrasPlantas = in.readDouble();
        profundidadNecesaria = in.readDouble();
        volumenNecesario = in.readDouble();
        nAbono = in.readString();
        nRiego = in.readString();
        nSol = in.readString();
        tipoSuelo = in.readString();
        nTemperatura = in.readString();
        urlimagen = in.readString();
    }

    public static final Parcelable.Creator<Planta> CREATOR = new Parcelable.Creator<Planta>() {
        @Override
        public Planta createFromParcel(Parcel in) {
            return new Planta(in);
        }

        @Override
        public Planta[] newArray(int size) {
            return new Planta[size];
        }
    };


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

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getCuandoPlantar() {
        return cuandoPlantar;
    }

    public void setCuandoPlantar(String cuandoPlantar) {
        this.cuandoPlantar = cuandoPlantar;
    }

    public String getDiasCosecha() {
        return diasCosecha;
    }

    public void setDiasCosecha(String diasCosecha) {
        this.diasCosecha = diasCosecha;
    }

    public double getDistanciaPlantas() {
        return distanciaPlantas;
    }

    public void setDistanciaPlantas(double distanciaPlantas) {
        this.distanciaPlantas = distanciaPlantas;
    }

    public double getDistanciaOtrasPlantas() {
        return distanciaOtrasPlantas;
    }

    public void setDistanciaOtrasPlantas(double distanciaOtrasPlantas) {
        this.distanciaOtrasPlantas = distanciaOtrasPlantas;
    }

    public double getProfundidadNecesaria() {
        return profundidadNecesaria;
    }

    public void setProfundidadNecesaria(double profundidadNecesaria) {
        this.profundidadNecesaria = profundidadNecesaria;
    }

    public double getVolumenNecesario() {
        return volumenNecesario;
    }

    public void setVolumenNecesario(double volumenNecesario) {
        this.volumenNecesario = volumenNecesario;
    }

    public String getnAbono() {
        return nAbono;
    }

    public void setnAbono(String nAbono) {
        this.nAbono = nAbono;
    }

    public String getnRiego() {
        return nRiego;
    }

    public void setnRiego(String nRiego) {
        this.nRiego = nRiego;
    }

    public String getnSol() {
        return nSol;
    }

    public void setnSol(String nSol) {
        this.nSol = nSol;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    public String getnTemperatura() {
        return nTemperatura;
    }

    public void setnTemperatura(String nTemperatura) {
        this.nTemperatura = nTemperatura;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idPlanta);
        dest.writeString(this.nombre);
        dest.writeString(this.nombreCientifico);
        dest.writeString(this.clase);
        dest.writeString(this.cuandoPlantar);
        dest.writeString(this.diasCosecha);
        dest.writeDouble(this.distanciaPlantas);
        dest.writeDouble(this.distanciaOtrasPlantas);
        dest.writeDouble(this.profundidadNecesaria);
        dest.writeDouble(this.volumenNecesario);
        dest.writeString(this.nAbono);
        dest.writeString(this.nRiego);
        dest.writeString(this.nSol);
        dest.writeString(this.tipoSuelo);
        dest.writeString(this.nTemperatura);
        dest.writeString(this.urlimagen);
    }
}
