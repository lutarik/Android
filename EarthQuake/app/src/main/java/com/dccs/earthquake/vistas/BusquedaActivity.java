package com.dccs.earthquake.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dccs.earthquake.Adapters.BusquedaAdapter;
import com.dccs.earthquake.R;
import com.dccs.earthquake.clases.DatosTerremoto;

import java.util.LinkedList;
import java.util.List;

public class BusquedaActivity extends Activity {

    ListView busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        iniComponentes();
    }

    private void iniComponentes() {
        busqueda = (ListView) findViewById(R.id.lv_resultado);

        List<DatosTerremoto> datos = new LinkedList<DatosTerremoto>();

        datos = cargaDatos(datos, "10/02/2015", "Terremoto 1", 9);
        datos = cargaDatos(datos, "15/03/2015", "Terremoto 2", 5);
        datos = cargaDatos(datos, "20/04/2015", "Terremoto 3", 1);

        BusquedaAdapter adaptador = new BusquedaAdapter(this, R.layout.layout_search_list, datos);

        busqueda.setAdapter(adaptador);

        registerForContextMenu(busqueda);
    }


    //Creamos menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.lv_resultado) {
            //Creamos el menu
            getMenuInflater().inflate(R.menu.mnu_contextual_busqueda, menu);
            //Obtenemos la posicion en la que hemos pinchado
            int posicion = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;

            DatosTerremoto terremoto = (DatosTerremoto) busqueda.getAdapter().getItem(posicion);
            menu.setHeaderTitle(terremoto.getNombre());
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Intent detalle = new Intent(BusquedaActivity.this,BusquedaDetalleActivity.class);
        int itemId = item.getItemId();
        //Recuperamos el menu
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();


        if (itemId == R.id.detalle) {
            int posicion = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            DatosTerremoto terremoto = (DatosTerremoto) busqueda.getAdapter().getItem(posicion);
            detalle.putExtra("terremoto",terremoto);
            startActivity(detalle);
        }

        return super.onContextItemSelected(item);
    }

    private List<DatosTerremoto> cargaDatos(List<DatosTerremoto> datos, String fecha, String nombre, float mag) {

        datos.add(new DatosTerremoto(fecha, nombre, mag));
        return datos;
    }


}
