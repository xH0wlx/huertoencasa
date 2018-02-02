package com.example.alink.huerto;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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

/**
 * Created by Nicolas on 10-07-16.
 */
public class DetalleReceta extends AppCompatActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    CollectionRecetaAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    private static String contenido;
    private ProgressDialog dialog;
    List<String> detalles;
    private String imagen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_aprender);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Recuperando información...");
        dialog.setCancelable(false);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        Bundle b = getIntent().getExtras();
        Receta receta = (Receta) b.get("receta");

        detalles = new ArrayList<>();

        detalles.add(receta.getNombre());
        detalles.add(receta.getIngredientes());
        detalles.add(receta.getPreparacion());
        imagen = receta.getImagen();

        mDemoCollectionPagerAdapter = new CollectionRecetaAdapter(getSupportFragmentManager(), detalles, imagen);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);


    }
}

    // Since this is an object collection, use a FragmentStatePagerAdapter,
    // and NOT a FragmentPagerAdapter.
    class CollectionRecetaAdapter extends FragmentStatePagerAdapter {
        List<String> items;
        String imagen;

        public CollectionRecetaAdapter(FragmentManager fm, List items, String imagen) {
            super(fm);
            this.items = items;
            this.imagen = imagen;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new RecetaFragment(items.get(i), i, imagen);
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(RecetaFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String returnString = "";
        switch (position){
            case 0:
                returnString = items.get(position);
                break;
            case 1:
                returnString = "Ingredientes";
                break;
            case 2:
                returnString =  "Preparacion";
                break;
        }
        return returnString;
    }
}

    // Instances of this class are fragments representing a single
    // object in our collection.
    @SuppressLint("ValidFragment")
    class RecetaFragment extends Fragment {
        public static final String ARG_OBJECT = "object";
        public String item;
        public int position;
        public String imagen;

        public RecetaFragment(String item, int position, String imagen) {
            this.item = item;
            this.position = position;
            this.imagen = imagen;
        }

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(
                    R.layout.fragment_collection_object_detalle_receta, container, false);

                ImageView imagenView = (ImageView) rootView.findViewById(R.id.imagenReceta);
                new DownloadImageTask(imagenView).execute(imagen);
            if(position == 0)
                imagenView.setVisibility(View.VISIBLE);
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(Html.fromHtml(item));

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

