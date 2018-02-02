package com.example.alink.huerto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nicolas on 08-07-2016.
 */
public class RecyclerViewAdapterCultivos extends RecyclerView.Adapter<RecyclerViewAdapterCultivos.CultivoViewHolder> implements View.OnClickListener {
    private List<Cultivo> items;
    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CultivoViewHolder extends RecyclerView.ViewHolder {
        public TextView cultivoId, cultivoLargo, cultivoAncho, cultivoProfundidad, cultivoSuelo;

        public CultivoViewHolder(View v) {
            super(v);
            cultivoId = (TextView) v.findViewById(R.id.data_cultivo_id);
            cultivoLargo = (TextView) v.findViewById(R.id.data_cultivo_largo);
            cultivoAncho = (TextView) v.findViewById(R.id.data_cultivo_ancho);
            cultivoProfundidad = (TextView) v.findViewById(R.id.data_cultivo_profundidad);
            cultivoSuelo = (TextView) v.findViewById(R.id.data_cultivo_suelo);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapterCultivos(List<Cultivo> items) {
        this.items = items;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CultivoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cultivo_card, parent, false);

        v.setOnClickListener(this);

        CultivoViewHolder vh = new CultivoViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CultivoViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cultivoId.setText("Cultivo " + (position+1));
        holder.cultivoLargo.setText("Largo: " + items.get(position).getLargo());
        holder.cultivoAncho.setText("Ancho: " + items.get(position).getAncho());
        holder.cultivoProfundidad.setText("Profundidad: " + items.get(position).getProfundidad());
        holder.cultivoSuelo.setText("Suelo: " + items.get(position).getTipoSuelo());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

}
