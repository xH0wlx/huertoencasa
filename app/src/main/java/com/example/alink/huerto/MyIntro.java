package com.example.alink.huerto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import bd.BdSqliteHelper;

public class MyIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        //addSlide(first_fragment);
        //addSlide(second_fragment);
        //addSlide(third_fragment);
        //addSlide(fourth_fragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("Bienvenido a Tu Huerto En Casa", "Vamos a mostrarte las funciones en este tutorial",R.mipmap.huertos , Color.GREEN));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        showDoneButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        //setVibrate(true);
        //setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        deshabilitarTutorial();
        Intent i = new Intent(this, PrincipalActivity.class);
        startActivity(i);
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    private void deshabilitarTutorial(){
        BdSqliteHelper bdplantas = new BdSqliteHelper(this, "DBPLANTAS", null, 1);
        SQLiteDatabase bd = bdplantas.getWritableDatabase();
        bd.execSQL("DELETE FROM Tutorial WHERE activo = 0");
        bd.execSQL("INSERT INTO Tutorial (activo) VALUES (1)");
    }
}