package com.example.alink.huerto;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

import bd.BdSqliteHelper;
import cz.msebera.android.httpclient.Header;

public class ListaRecetasActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterRecetas mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog dialog;
    private List<Receta> items;
    private ArrayList<PlantaAux> plantasEnCultivo;
    private String idCultivo;
    private Cultivo cultivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recetas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Recuperando información...");
        dialog.setCancelable(false);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_recetas);
        mRecyclerView.setHasFixedSize(true);

        Bundle b = getIntent().getExtras();
        cultivo = (Cultivo)b.get("cultivo");

        plantasEnCultivo = new ArrayList<>();
        idCultivo = String.valueOf(cultivo.getIdCultivo());
        traerPlantasDeCultivo();

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


        String idsPlantas = "";

        for (int i = 0; i < plantasEnCultivo.size()-1; i++){
            System.out.println("ID PLANTA : " + plantasEnCultivo.get(i).getIdPlanta());
            idsPlantas += plantasEnCultivo.get(i).getIdPlanta()+',';
        }

        idsPlantas += plantasEnCultivo.get(plantasEnCultivo.size()-1).getIdPlanta();


        RequestParams obj = new RequestParams();
        obj.put("c", "Receta");
        obj.put("a", "getReceta");
        obj.put("ids", idsPlantas);
        tarea(obj);
    }

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
                        JSONArray arreglo = response.getJSONArray("recetas");
                        JSONObject receta;

                        for (int i=0; i<arreglo.length();i++){
                            JSONObject aux = arreglo.getJSONObject(i);
                            receta = aux.getJSONObject("receta");

                            Receta receta1 = new Receta(
                                    receta.getInt("idReceta"),
                                    receta.getString("nombre"),
                                    receta.getString("ingredientes"),
                                    receta.getString("tipo"),
                                    receta.getString("preparacion"),
                                    receta.getString("imagen")
                            );

                            if(!items.contains(receta1)){
                                items.add(receta1);
                            }

                        }
                        //Crear nuevo adaptador
                        mAdapter = new RecyclerViewAdapterRecetas(items, getApplicationContext());
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
