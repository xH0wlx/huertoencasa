package com.example.alink.huerto;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OpcionesCultivoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterOpcion mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<OpcionCultivo> items;
    private Cultivo cultivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_cultivo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_opciones_cultivo);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Bundle b = getIntent().getExtras();
        cultivo = (Cultivo)b.get("cultivo");

        if(cultivo != null) {
            items = new ArrayList<>();
            items.add(new OpcionCultivo("Plantas", "Revisa las plantas que están actualmente en tu cultivo"));
            items.add(new OpcionCultivo("Recetas", "Mira las recetas que puedes preparar con las plantas de tu cultivo"));

            mAdapter = new RecyclerViewAdapterOpcion(items, cultivo);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
