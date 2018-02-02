package com.example.alink.huerto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AprenderActivity extends AppCompatActivity {

    private TextView aprender_seccion;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterAprender mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Aprender> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aprender_seccion = (TextView) findViewById(R.id.aprender_seccion);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_aprender);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        items = new ArrayList<>();
        items.add(new Aprender("Herramientas", "", "Herramienta"));
        items.add(new Aprender("Técnicas", "", "Tecnica"));
        items.add(new Aprender("Consejos", "", "Consejo"));

        mAdapter = new RecyclerViewAdapterAprender(items);
        mRecyclerView.setAdapter(mAdapter);
    }
}
