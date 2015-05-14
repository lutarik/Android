package com.dccs.earthquake.vistas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dccs.earthquake.Adapters.BusquedaAdapter;
import com.dccs.earthquake.R;
import com.dccs.earthquake.clases.DatosTerremoto;

import java.util.LinkedList;
import java.util.List;

public class ResultadoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        iniComponentes();
    }

    private void iniComponentes() {
        ListView busqueda = (ListView) findViewById(R.id.lv_resultado);

        List<DatosTerremoto> datos = new LinkedList<DatosTerremoto>();

        datos = cargaDatos(datos, "10/02/2015", "Terremoto 1", 9);
        datos = cargaDatos(datos, "15/03/2015", "Terremoto 2", 5);
        datos = cargaDatos(datos, "20/04/2015", "Terremoto 3", 1);

        BusquedaAdapter adaptador = new BusquedaAdapter(this, R.layout.layout_search_list, datos);

        busqueda.setAdapter(adaptador);

    }

    private List<DatosTerremoto> cargaDatos(List<DatosTerremoto> datos, String fecha, String nombre, float mag) {

        datos.add(new DatosTerremoto(fecha, nombre, mag));
        return datos;
    }

}
