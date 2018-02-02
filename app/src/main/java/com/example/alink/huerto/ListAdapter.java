package com.example.alink.huerto;

/**
 * Created by Luis on 19-06-2016.
 */
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 12/10/2015.
 */
public class ListAdapter extends ArrayAdapter<PlantaAux> {

    private final Activity context;
    private final ArrayList<PlantaAux>plantas;



    public ListAdapter(Activity context,  ArrayList<PlantaAux> itemname) {
        super(context, R.layout.fila_lista, itemname);

        this.context=context;
        this.plantas=itemname;

    }

    public View getView(int posicion,View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.fila_lista,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto_principal);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView etxDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);
        TextView textTipo = (TextView) rowView.findViewById(R.id.texto_tipo);

        txtTitle.setText("Nombre: "+plantas.get(posicion).getNombre());
        new DownloadImageTask(imageView).execute("http://colvin.chillan.ubiobio.cl:8070/nionate/webservice/assets/img/ajo.jpg");
        //imageView.setImageResource(integers[posicion]);
        etxDescripcion.setText("Cantidad: "+plantas.get(posicion).getCantidad());
        textTipo.setText("Fecha: "+plantas.get(posicion).getFecha());

        return rowView;
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
            bmImage.setScaleType(ImageView.ScaleType.FIT_XY);
            bmImage.setImageBitmap(result);
        }
    }


}
