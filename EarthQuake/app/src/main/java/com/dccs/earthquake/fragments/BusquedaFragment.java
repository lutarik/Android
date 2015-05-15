package com.dccs.earthquake.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dccs.earthquake.R;
import com.dccs.earthquake.adapters.BusquedaAdapter;
import com.dccs.earthquake.clases.DatosTerremoto;

import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaFragment extends Fragment {


    ListView busqueda;

    public BusquedaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.frg_busqueda, container, false);


        List<DatosTerremoto> datos = new LinkedList<DatosTerremoto>();

        datos = cargaDatos(datos, "10/02/2015", "Terremoto 1", 9);
        datos = cargaDatos(datos, "15/03/2015", "Terremoto 2", 5);
        datos = cargaDatos(datos, "20/04/2015", "Terremoto 3", 1);

        BusquedaAdapter adaptador = new BusquedaAdapter(container.getContext(), R.layout.layout_search_list, datos);

        busqueda.setAdapter(adaptador);

        // Inflate the layout for this fragment
        return vista;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void registerListViewDTOnClickListener(View.OnClickListener listener) {
        busqueda.setOnClickListener(listener);
    }


    private List<DatosTerremoto> cargaDatos(List<DatosTerremoto> datos, String fecha, String nombre, float mag) {

        datos.add(new DatosTerremoto(fecha, nombre, mag));
        return datos;
    }
}
