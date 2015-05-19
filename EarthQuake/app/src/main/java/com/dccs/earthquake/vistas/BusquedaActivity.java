package com.dccs.earthquake.vistas;

import android.app.Activity;
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
    DatosTerremoto terr_busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        terr_busqueda = (DatosTerremoto) getIntent().getSerializableExtra("busqueda");

        iniComponentes();
    }

    private void iniComponentes() {
        busqueda = (ListView) findViewById(R.id.lv_resultado);

        List<DatosTerremoto> datos = new LinkedList<DatosTerremoto>();
        List<DatosTerremoto> res_busqda = new LinkedList<DatosTerremoto>();

        //Cargamos los datos abajo indicados
        datos = cargaDatos(datos, "10/01/2015", "Terremoto 1", 9);
        datos = cargaDatos(datos, "15/02/2015", "Terremoto 2", 5);
        datos = cargaDatos(datos, "20/03/2015", "Terremoto 3", 1);
        datos = cargaDatos(datos, "12/04/2015", "Terremoto 4", 1);
        datos = cargaDatos(datos, "05/05/2015", "Terremoto 5", 1);
        datos = cargaDatos(datos, "10/04/2015", "Terremoto 6", 9);
        datos = cargaDatos(datos, "10/03/2015", "Terremoto 7", 9);
        datos = cargaDatos(datos, "15/02/2015", "Terremoto 8", 5);
        datos = cargaDatos(datos, "15/01/2015", "Terremoto 9", 5);



        //Creamos el adaptador indicandole la Vista (this), como lo queremos mostrar (layout_search_list) y los datos que queremos mostrar (datos)
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


        int itemId = item.getItemId();

        if (itemId == R.id.detalle) {
            ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();

            int posicion = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            DatosTerremoto itemT = (DatosTerremoto) busqueda.getAdapter().getItem(posicion);

            /*
             * COMPROBAMOS LO QUE VAMOS A REALIZAR.
             * PODEMOS COMPROBAR SI TENEMOS UN FRAGMENTO O NO PARA DIFERENCIAR ENTRE TABLET O MVL
             * */
            BusquedaDetalleFragment hayfragment = (BusquedaDetalleFragment) getFragmentManager().findFragmentById(R.id.frag_busqueda_detalle);

            //Si hay fragment se carga para tablet, sino para smartphone
            if (hayfragment != null) {
                hayfragment.actualizarDetalle(itemT);
            } else {
                Intent detalle = new Intent(BusquedaActivity.this, BusquedaDetalleActivity.class);
                detalle.putExtra("item", itemT);
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
