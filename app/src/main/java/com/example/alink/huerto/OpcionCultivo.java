package com.example.alink.huerto;

import android.os.Parcel;
import android.os.Parcelable;

public class OpcionCultivo implements Parcelable {

        private String nombre, descripcion;

        public OpcionCultivo(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        protected OpcionCultivo(Parcel in) {
            nombre = in.readString();
            descripcion = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(nombre);
            dest.writeString(descripcion);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<OpcionCultivo> CREATOR = new Creator<OpcionCultivo>() {
            @Override
            public OpcionCultivo createFromParcel(Parcel in) {
                return new OpcionCultivo(in);
            }

            @Override
            public OpcionCultivo[] newArray(int size) {
                return new OpcionCultivo[size];
            }
        };

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