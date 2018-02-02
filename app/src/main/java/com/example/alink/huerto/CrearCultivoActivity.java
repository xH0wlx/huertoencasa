package com.example.alink.huerto;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import bd.BdSqliteHelper;

public class CrearCultivoActivity extends AppCompatActivity {

    private Spinner tipoSuelo;
    private EditText txt_cultivo_largo, txt_cultivo_ancho, txt_cultivo_profundidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cultivo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_cultivo_largo = (EditText)findViewById(R.id.txt_cultivo_largo);
        txt_cultivo_ancho = (EditText)findViewById(R.id.txt_cultivo_ancho);
        txt_cultivo_profundidad = (EditText)findViewById(R.id.txt_cultivo_profundidad);
        tipoSuelo = (Spinner) findViewById(R.id.cultivo_tipo_suelo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_tipo_suelo, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipoSuelo.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void guardarCultivo(View v){
        BdSqliteHelper bdplantas = new BdSqliteHelper(this, "DBPLANTAS", null, 1);
        SQLiteDatabase bd = bdplantas.getWritableDatabase();

        Float largo = Float.parseFloat(txt_cultivo_largo.getText().toString());
        Float ancho = Float.parseFloat(txt_cultivo_ancho.getText().toString());
        Float profundidad = Float.parseFloat(txt_cultivo_profundidad.getText().toString());
        String tipo_suelo = tipoSuelo.getSelectedItem().toString();

        bd.execSQL("INSERT INTO Cultivo(largo, ancho, profundidad, tipoSuelo) VALUES("+largo+", " +
                ""+ancho+", "+profundidad+", '"+tipo_suelo+"')");

        //Mensaje para el usuario
        Toast.makeText(CrearCultivoActivity.this, "Datos del cultivo guardados", Toast.LENGTH_SHORT).show();

        //se redirecciona a la pantalla principal
        Intent i = new Intent(this, PrincipalActivity.class);

        //cierra todas las actividades (asi al volver atras no vuelve a una pantalla que no deberia ver)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //preparacion para iniciar la nueva activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        this.startActivity(i);

    }
}
