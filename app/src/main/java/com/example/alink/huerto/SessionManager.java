package com.example.alink.huerto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.net.IDN;
import java.util.HashMap;

/**
 * Created by Alink on 04-07-2016.
 */
public class SessionManager {
    //objeto principal para simular una sesion en android
    private SharedPreferences preferences;
    //editor para las preferencias
    private SharedPreferences.Editor editor;
    //contexto de la app
    private Context context;

    public SessionManager(Context context){

        this.context = context;
        preferences = context.getSharedPreferences(Constantes.PREF_NAME, Constantes.PRIVATE_MODE); //recibe nombre y modo (privado = 0)
        editor = preferences.edit();
    }

    public void crearSesion(String name, String email, int idUsuario){

        editor.putBoolean(Constantes.IS_LOGIN, true);
        editor.putString(Constantes.NAME_KEY, name);
        editor.putString(Constantes.EMAIL_KEY, email);
        editor.putInt(Constantes.IDUSUARIO, idUsuario);

        editor.commit();
    }

    public HashMap<String, String> detallesUsuario(){

        HashMap<String, String> usuario = new HashMap<>();

        usuario.put(Constantes.NAME_KEY, preferences.getString(Constantes.NAME_KEY, null));
        usuario.put(Constantes.EMAIL_KEY, preferences.getString(Constantes.EMAIL_KEY, null));
        usuario.put(Constantes.IDUSUARIO, String.valueOf(preferences.getInt(Constantes.IDUSUARIO, -1)));

        return usuario;
    }

    public void checkLogin(){

        if(!this.estaLogueado()){
            Intent i = new Intent(context, LoginActivity.class);

            //cierra todas las actividades (asi al volver atras no vuelve a una pantalla que no deberia ver)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            //preparacion para iniciar la nueva activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);
        }
    }

    public void cerrarSesion(){
        //se limpia toda la informacion de las preferencias
        editor.clear();
        editor.commit();

        //se redirecciona a la pantalla principal
        Intent i = new Intent(context, LoginActivity.class);

        //cierra todas las actividades (asi al volver atras no vuelve a una pantalla que no deberia ver)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //preparacion para iniciar la nueva activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

    public boolean estaLogueado(){
        return preferences.getBoolean(Constantes.IS_LOGIN, false);
    }
}
