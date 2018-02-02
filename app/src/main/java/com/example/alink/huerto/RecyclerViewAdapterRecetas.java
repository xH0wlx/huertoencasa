package com.example.alink.huerto;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Nicolas on 10-07-16.
 */
public class RecyclerViewAdapterRecetas extends RecyclerView.Adapter<RecyclerViewAdapterRecetas.RecetaViewHolder> {

    private static List<Receta> items;
    public static Context context;

    public static class RecetaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recetaTitulo;
        public ImageView fondoImagen;
        public int position;


        public RecetaViewHolder(View v) {
            super(v);
            recetaTitulo = (TextView) v.findViewById(R.id.receta_titulo);
            fondoImagen = (ImageView) v.findViewById(R.id.imagen_fondo_receta);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Receta receta = items.get(position);
            Intent i = new Intent(v.getContext(), DetalleReceta.class);
            i.putExtra("receta", receta);
            v.getContext().startActivity(i);
        }
    }

    public RecyclerViewAdapterRecetas(List<Receta> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receta_card, parent, false);

        RecetaViewHolder vh = new RecetaViewHolder(v);
        return vh;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecetaViewHolder holder, int position) {
        holder.recetaTitulo.setText(items.get(position).getNombre());
        System.out.println();
        new DownloadImageTask(holder.fondoImagen).execute(items.get(position).getImagen());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setAdjustViewBounds(true);
            bmImage.setScaleType(ImageView.ScaleType.FIT_XY );
            bmImage.setImageBitmap(result);
        }
    }
}
