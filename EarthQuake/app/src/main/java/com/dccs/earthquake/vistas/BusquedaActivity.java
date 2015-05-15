package com.dccs.earthquake.vistas;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dccs.earthquake.R;
import com.dccs.earthquake.adapters.BusquedaAdapter;
import com.dccs.earthquake.clases.DatosTerremoto;
import com.dccs.earthquake.fragments.BusquedaDetalleFragment;

import java.util.LinkedList;
import java.util.List;

public class BusquedaActivity extends Activity implements View.OnClickListener {

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

        //Cargamos los datos abajo indicados
        datos = cargaDatos(datos, "10/02/2015", "Terremoto 1", 9);
        datos = cargaDatos(datos, "15/03/2015", "Terremoto 2", 5);
        datos = cargaDatos(datos, "20/04/2015", "Terremoto 3", 1);

        //Creamos el adaptador indicandole la Vista (this), como lo queremos mostrar (layout_search_list) y los datos que queremos mostrar (datos)
        BusquedaAdapter adaptador = new BusquedaAdapter(this, R.layout.layout_search_list, datos);

        busqueda.setAdapter(adaptador);

        registerForContextMenu(busqueda);


/*        BusquedaFragment frgTerremoto = (BusquedaFragment) getFragmentManager().findFragmentById(R.id.frg_busqueda);

        frgTerremoto.registerListViewDTOnClickListener(this);*/
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

        Intent detalle = new Intent(BusquedaActivity.this, BusquedaDetalleActivity.class);
        int itemId = item.getItemId();
        //Recuperamos el menu
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();


        /*
        * COMPROBAMOS LO QUE VAMOS A REALIZAR.
        * PODEMOS COMPROBAR SI TENEMOS UN FRAGMENTO O NO PARA DIFERENCIAR ENTRE TABLET O MVL
        * */
        BusquedaDetalleFragment hayfragment = (BusquedaDetalleFragment) getFragmentManager().findFragmentById(R.id.frg_busqueda_detalle);

        if (hayfragment != null) {

            if (itemId == R.id.detalle) {
                int posicion = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
                DatosTerremoto terremoto = (DatosTerremoto) busqueda.getAdapter().getItem(posicion);
                hayfragment.actualizarDetalle(terremoto);
            }
        } else {
            if (itemId == R.id.detalle) {
                int posicion = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
                DatosTerremoto terremoto = (DatosTerremoto) busqueda.getAdapter().getItem(posicion);
                detalle.putExtra("terremoto", terremoto);
                startActivity(detalle);
            }
        }


        return super.onContextItemSelected(item);
    }

    private List<DatosTerremoto> cargaDatos(List<DatosTerremoto> datos, String fecha, String nombre, float mag) {

        datos.add(new DatosTerremoto(fecha, nombre, mag));
        return datos;
    }


    @Override
    public void onClick(View v) {

    }
}
