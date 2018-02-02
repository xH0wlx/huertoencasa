package com.example.alink.huerto;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Alink on 04-07-2016.
 */
public class RegisterManager {

    private String username;
    private String email;
    private String password;
    private ProgressDialog dialog;
    private final Context context;

    public RegisterManager(Context context, String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.context = context;
        dialog = new ProgressDialog(this.context);
        dialog.setMessage("Registrando usuario...");
        dialog.setCancelable(false);
    }

    public void registerUser(){

        RequestParams params = new RequestParams();
        params.put("username", this.username);
        params.put("email", this.email);
        params.put("password", this.password);

        dialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://colvin.chillan.ubiobio.cl:8070/nionate/webservice/?c=User&a=registerUser", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 200){
                    dialog.dismiss();
                    try {
                        System.out.println(response.get("response"));
                        Toast.makeText(context, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, PrincipalActivity.class);
                        //cierra todas las actividades (asi al volver atras no vuelve a una pantalla que no deberia ver)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        //preparacion para iniciar la nueva activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
