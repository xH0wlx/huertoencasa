package com.example.alink.huerto;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ListaDePlantasActivity extends AppCompatActivity {
    //LINEAR LAYOUT MANAGER
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_plantas);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Recuperando información...");
        dialog.setCancelable(false);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RequestParams obj = new RequestParams();
        obj.put("c", "Planta");
        obj.put("a", "getallplantas");

        // Inicializar Animes
        tarea(obj);

        /*//Crear nuevo adaptador
        mAdapter = new ReciclerViewAdapter(items);
        mRecyclerView.setAdapter(mAdapter);*/


    }//FIN ON CREATE

   public void tarea(RequestParams params){
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        final List items = new ArrayList();

        client.get("http://colvin.chillan.ubiobio.cl:8070/nionate/webservice/?", params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(statusCode == 200){
                    dialog.dismiss();
                    try {
                        JSONArray arreglo = response.getJSONArray("plantas");
                        JSONObject planta;

                        for (int i=0; i<arreglo.length();i++){
                            Planta plantita = new Planta();

                            JSONObject aux = arreglo.getJSONObject(i);
                            planta = aux.getJSONObject("planta");

                            plantita.setIdPlanta(planta.getString("idPlanta"));
                            plantita.setNombre(planta.getString("nombre"));
                            plantita.setNombreCientifico(planta.getString("nomCientifico"));
                            plantita.setClase(planta.getString("clase"));

                            plantita.setCuandoPlantar(planta.getString("cuandoPlantar"));
                            plantita.setDiasCosecha(planta.getString("diasCosecha"));

                            plantita.setDistanciaPlantas(planta.getDouble("distanciaPlantas[cm]"));
                            plantita.setDistanciaOtrasPlantas(planta.getDouble("distanciaOtrasPlantas[cm]"));
                            plantita.setProfundidadNecesaria(planta.getDouble("profundidadNecesaria[cm]"));
                            plantita.setVolumenNecesario(planta.getDouble("volumenNecesario[l]"));
                            plantita.setnAbono(planta.getString("nAbono"));
                            plantita.setnRiego(planta.getString("nRiego"));
                            plantita.setnSol(planta.getString("nSol"));
                            plantita.setTipoSuelo(planta.getString("tipoSuelo"));
                            plantita.setnTemperatura(planta.getString("nTemperatura"));

                            plantita.setUrlimagen(planta.getString("urlimagen"));

                            items.add(plantita);
                        }
                        //Crear nuevo adaptador
                        mAdapter = new ReciclerViewAdapter(items);
                        mRecyclerView.setAdapter(mAdapter);

                    }catch(JSONException e){

                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
       //return items;
   }//FIN TAREA
}
