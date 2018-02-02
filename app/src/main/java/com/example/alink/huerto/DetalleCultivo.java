package com.example.alink.huerto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bd.BdSqliteHelper;

public class DetalleCultivo extends AppCompatActivity {
    private ListView listaEnCultivo;
    Planta planta;
    String idCultivo;
    ArrayList<PlantaAux> plantasEnCultivo;
    TextView titulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cultivo);

        //Bundle b = getIntent().getExtras();
        //planta = (Planta)b.get("planta");
        titulo = (TextView)findViewById(R.id.tituloDetalleCultivo);

        idCultivo = getIntent().getStringExtra("idCultivo");

        titulo.setText("Cultivo N° "+idCultivo);

        plantasEnCultivo = new ArrayList<PlantaAux>();
        traerPlantasDeCultivo();//DESDE LA BASE DE DATOS LOCAL
        if(plantasEnCultivo.size() == 0){
            Toast.makeText(getApplicationContext(), "No tienes plantas en este cultivo", Toast.LENGTH_SHORT).show();
        }
        ListAdapter adapter=new ListAdapter(this,plantasEnCultivo);
        listaEnCultivo=(ListView)findViewById(R.id.listaEnCultivo);
        listaEnCultivo.setAdapter(adapter);

        listaEnCultivo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Slecteditem= plantasEnCultivo.get(position).toString();
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }
        });
    }//FIN ONCREATE

    private void traerPlantasDeCultivo(){
        BdSqliteHelper bdplantas = new BdSqliteHelper(this, "DBPLANTAS", null, 1);
        SQLiteDatabase bd = bdplantas.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CultivoContienePlantas where idCultivo = "+idCultivo+";", null);
        if (cursor.moveToFirst()) {
            do {
                plantasEnCultivo.add(new PlantaAux(cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
