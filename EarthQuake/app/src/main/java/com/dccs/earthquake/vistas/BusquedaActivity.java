package com.dccs.earthquake.vistas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dccs.earthquake.R;
import com.dccs.earthquake.clases.DatosTerremoto;
import com.dccs.earthquake.clases.DescargaDatosTerremotos;
import com.dccs.earthquake.fragments.BusquedaDetalleFragment;

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
        SharedPreferences preferencias = getSharedPreferences("Preferencias EarthQuake",MODE_PRIVATE);
        busqueda = (ListView) findViewById(R.id.lv_resultado);

        ArrayAdapter<DatosTerremoto> adapter= new ArrayAdapter<>(this,R.layout.layout_search_list);
        busqueda.setAdapter(adapter);

        DescargaDatosTerremotos datos= new DescargaDatosTerremotos(adapter);

        datos.execute(preferencias.getString("URL",null));

/*        //Creamos el adaptador indicandole la Vista (this), como lo queremos mostrar (layout_search_list) y los datos que queremos mostrar (datos)
        DatosTerremotoAdapter adaptador = new DatosTerremotoAdapter(this, R.layout.layout_search_list);

        busqueda.setAdapter(adaptador);
*/
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
            menu.setHeaderTitle(terremoto.getTitulo());
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


    @Override
    public void onClick(View v) {

    }
}
