package com.example.alink.huerto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Alink on 04-07-2016.
 */
public class WelcomeActivity extends AppCompatActivity {

    private TextView bienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        bienvenida = (TextView)findViewById(R.id.bienvenida);

        Bundle b = getIntent().getExtras();

        String username = b.getString(Constantes.NAME_KEY);

        String msg = "BIENVENIDO " + username.toUpperCase();
        bienvenida.setText(msg);
    }
}
