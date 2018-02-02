package com.example.alink.huerto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DetallePlantaActivity extends AppCompatActivity {
    TextView texto;
    Planta planta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_planta);

        //CAMPOS VISTA

        TextView tDatosPlanta = (TextView)findViewById(R.id.tDatosPlanta);

        Bundle b = getIntent().getExtras();
        planta = (Planta)b.get("planta");


        String datosPlantaAux =
                "DATOS GENERALES:"+"\n\n"+
                "Nombre: "+planta.getNombre()+"\n"+
                "Nombre Científico: "+planta.getNombreCientifico()+"\n"+
                "Clase: "+planta.getClase()+"\n"+
                "\n\nDATOS DE PLANTACIÓN"+"\n\n"+
                "¿Cuándo Plantar?: "+planta.getCuandoPlantar()+"\n"+
                "¿Cuándo Cosechar?: "+planta.getDiasCosecha()+"\n"+
                "Distancia entre líneas de plantación en el huerto: "+planta.getDistanciaPlantas()+" [cm]\n"+
                "Distancia entre una planta y otra en el huerto: "+planta.getDistanciaOtrasPlantas()+" [cm]\n"+
                "Profundidad necesaria para plantar: "+planta.getProfundidadNecesaria()+" [cm]\n"+
                "Volumen necesario para planta: "+planta.getVolumenNecesario()+" [l]\n"+
                        "\n\nNECESIDADES DE LA PLANTA:"+"\n"+
                "\nNecesidades de abono: "+planta.getnAbono()+"\n"+
                "\nNecesidades de riego: "+planta.getnRiego()+"\n"+
                "\nNecesidades de sol: "+planta.getnSol()+"\n"+
                "\nNecesidades de tipo de suelo: "+planta.getTipoSuelo()+"\n"+
                "\nNecesidades de Temperatura: "+planta.getnTemperatura();

        tDatosPlanta.setText(datosPlantaAux);


        Button agregarACultivo = (Button)findViewById(R.id.botonAgregar);

        agregarACultivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetallePlantaActivity.this, AgregarACultivoActivity.class);
                intent.putExtra("planta", planta);
                startActivity(intent);
            }
        });
    }

}
