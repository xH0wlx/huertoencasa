package com.example.alink.huerto;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alink on 08-07-2016.
 */
public class RecyclerViewAdapterAprender extends RecyclerView.Adapter<RecyclerViewAdapterAprender.AprenderViewHolder>  {

    private static List<Aprender> items;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class AprenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombreSeccion, detalleSeccion;
        public int position;

        public AprenderViewHolder(View v) {
            super(v);
            nombreSeccion = (TextView) v.findViewById(R.id.aprender_seccion);
            detalleSeccion = (TextView) v.findViewById(R.id.aprender_detalle_seccion);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Aprender aprender = items.get(position);
            Intent i = null;
            switch (aprender.getNombreTabla()){
                case "Herramienta":
                    i = new Intent(v.getContext(), DetalleAprenderHerramientasActivity.class);
                    break;
                case "Consejo":
                    i = new Intent(v.getContext(), DetalleAprenderConsejosActivity.class);
                    break;
                case "Tecnica":
                    i = new Intent(v.getContext(), DetalleAprenderTecnicasActivity.class);
                    break;
            }

            i.putExtra("aprender", aprender);
            v.getContext().startActivity(i);
        }
    }

    public RecyclerViewAdapterAprender(List<Aprender> items) {
        this.items = items;
    }

    @Override
    public AprenderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aprender_card, parent, false);

        AprenderViewHolder vh = new AprenderViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AprenderViewHolder holder, int position) {

        holder.nombreSeccion.setText(items.get(position).getNombreSeccion());
        holder.detalleSeccion.setText(items.get(position).getDetalleSeccion());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
