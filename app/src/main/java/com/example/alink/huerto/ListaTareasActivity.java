package com.example.alink.huerto;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.ListAdapter;

import java.util.ArrayList;

import bd.BdSqliteHelper;
import fragments.List;

public class ListaTareasActivity extends AppCompatActivity {

    private ListView listaTareas;
    private ArrayList<Tarea> tareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tareas = new ArrayList<>();
        recuperarListaTareas();

        ListAdapterTarea adapterTarea = new ListAdapterTarea(this, tareas);

        listaTareas = (ListView)findViewById(R.id.list_tareas);
        listaTareas.setAdapter(adapterTarea);

    }

    private void recuperarListaTareas(){
        BdSqliteHelper bdplantas = new BdSqliteHelper(this, "DBPLANTAS", null, 1);
        SQLiteDatabase bd = bdplantas.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM Tarea", null);
        if (cursor.moveToFirst()) {
            do {
                tareas.add(new Tarea(cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
