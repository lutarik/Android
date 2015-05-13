package com.dccs.earthquake.vistas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.dccs.earthquake.R;
import com.dccs.earthquake.clases.Informacion;


public class HelpActivity extends Activity {

    private TextView l_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        String nombre;
        //Cogemos la Actividad que nos ha llamado y cogemos lo que nos ha pasado con putExtra
        Informacion dato = (Informacion) getIntent().getExtras().getSerializable("dato");
        nombre=dato.getNombre();
        iniComponentes();
        l_texto.setText("Hola " + nombre + " bienvenido a HearthQuacke!!");
    }

    private void iniComponentes() {
        l_texto = (TextView) findViewById(R.id.lbl_welcome);
    }
}
