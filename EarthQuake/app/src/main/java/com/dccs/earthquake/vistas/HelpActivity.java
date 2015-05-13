package com.dccs.earthquake.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dccs.earthquake.clases.Informacion;
import com.dccs.earthquake.R;


public class HelpActivity extends Activity {

    private TextView bienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Cogemos la Actividad que nos ha llamado y cogemos lo que nos ha pasado con putExtra
        Informacion dato = (Informacion) getIntent().getExtras().getSerializable("datos");

        bienvenido = (TextView) findViewById(R.id.welcome);
        bienvenido.setText("Hola " + dato.getNombre() + " bienvenido a HearthQuacke!!");
        //

    }
}
