package com.example.alink.huerto;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DetalleAprenderHerramientasActivity extends AppCompatActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    private static String contenido;
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_aprender);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Recuperando información...");
        dialog.setCancelable(false);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        Bundle b = getIntent().getExtras();
        Aprender aprender = (Aprender)b.get("aprender");
        String nombreTabla = aprender.getNombreTabla();

        System.out.println("NOMBRE TABLA: " + nombreTabla);

        RequestParams obj = new RequestParams();
        obj.put("c", "Aprender");
        obj.put("a", "getObjs");
        obj.put("name", nombreTabla);

        tarea(obj, nombreTabla);

    }


    public void tarea(RequestParams params, final String nombreTabla){
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        final List<Herramienta> items = new ArrayList();

        client.get("http://colvin.chillan.ubiobio.cl:8070/nionate/webservice/?", params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(statusCode == 200){
                    dialog.dismiss();
                    System.out.println("NOMBRE TABLAAAA: " + nombreTabla);
                    try {
                        JSONArray arreglo = response.getJSONArray("obj");
                        JSONObject objeto;

                        switch (nombreTabla) {
                            case "Herramienta":
                                System.out.println("I'M HERE MOTHERFUCKER");
                                for (int i=0; i<arreglo.length();i++){
                                    Herramienta herramienta;

                                    JSONObject aux = arreglo.getJSONObject(i);
                                    objeto = aux.getJSONObject("objeto");

                                    herramienta = new Herramienta(
                                            "",
                                            objeto.getString("nombre"),
                                            objeto.getString("descripcion"),
                                            objeto.getString("precio"),
                                            objeto.getString("imagen"));

                                    items.add(herramienta);
                                }

                                mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager(), items);
                                mViewPager = (ViewPager) findViewById(R.id.pager);
                                mViewPager.setAdapter(mDemoCollectionPagerAdapter);

                                break;
                            case "Consejo":
                                break;
                            case "Tecnica":
                                break;
                        }

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

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
    List<Herramienta> items;
    public DemoCollectionPagerAdapter(FragmentManager fm, List items) {
        super(fm);
        this.items = items;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DemoObjectFragment(items.get(i));
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return items.get(position).getNombre();
    }
}

// Instances of this class are fragments representing a single
// object in our collection.
@SuppressLint("ValidFragment")
class DemoObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    public Herramienta item;

    public DemoObjectFragment(Herramienta item){
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_collection_object_herramientas, container, false);
        Bundle args = getArguments();
        ((TextView) rootView.findViewById(R.id.descripcion_herramienta)).setText(item.getDescripcion());
        ((TextView) rootView.findViewById(R.id.precio_herramienta)).setText("Precio: " + item.getPrecio());

        new DownloadImageTask(((ImageView) rootView.findViewById(R.id.imagen_herramienta))).execute(item.getImagen());
        return rootView;
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


