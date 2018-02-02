package com.example.alink.huerto;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alink on 10-07-2016.
 */
public class ListAdapterTarea extends ArrayAdapter<Tarea>{

    String[] colorlist = {"#e00707", "#4ac925" };
    private final Activity context;
    private final ArrayList<Tarea> tareas;



    public ListAdapterTarea(Activity context,  ArrayList<Tarea> itemname) {
        super(context, R.layout.fila_lista, itemname);

        this.context=context;
        this.tareas=itemname;

    }

    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.detail_list_row,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.description);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imagen);

        txtTitle.setText(tareas.get(posicion).getNombre());
        int positionColor = 0;
        if(tareas.get(posicion).getEstado() == 1){
            positionColor = 1;
        }
        imageView.setBackgroundColor(Color.parseColor(colorlist[positionColor]));
        return rowView;
    }
}
