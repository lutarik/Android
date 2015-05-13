package com.dccs.earthquake.clases;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by androidm on 13/05/2015.
 */
public class BusquedaAdapter  extends BaseAdapter{

    private Context context;
    private int layout;
    private List<DatosTerremoto> datos;

    public BusquedaAdapter(Context context, int layout, List<DatosTerremoto> datos) {
        this.context = context;
        this.layout = layout;
        this.datos = datos;
    }

    //Items a poner
    @Override
    public int getCount() {
        return datos.size();
    }
    // Item en la posicion "position"
    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    // Id del item en la posicion "position"
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Damelo todo
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
