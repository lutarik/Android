package com.dccs.earthquake.vistas;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dccs.earthquake.R;

public class ResultadoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        iniComponentes();
    }

    private void iniComponentes(){
        ListView busqueda  = (ListView) findViewById(R.id.lst_resultado);
        String[] dato = new String[] {"Opcion 1", "Opcion 2", "Opcion 3"};
        ListAdapter datos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dato);
        busqueda.setAdapter(datos);

    }
}
