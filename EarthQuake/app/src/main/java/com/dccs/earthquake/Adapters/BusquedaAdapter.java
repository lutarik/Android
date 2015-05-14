package com.dccs.earthquake.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dccs.earthquake.R;
import com.dccs.earthquake.clases.DatosTerremoto;

import java.util.List;

/**
 * Created by androidm on 13/05/2015.
 */
public class BusquedaAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DatosTerremoto> datos;

    private TextView t_terremoto;
    private TextView t_magnitud;
    private ImageView icono;

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

    //Muestra los elementos de la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Si es la primera vez que lo pintamos
        if (convertView == null) {
            //Obtenemos el LayoutInflater
            LayoutInflater inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Reasignamos convertView para que nos valga para las dos opciones
            convertView= inflador.inflate(layout, null);

        }

        //Obtenemos el valor a mostrar
        DatosTerremoto item = (DatosTerremoto) getItem(position);

        //inicializamos componentes
        t_terremoto = (TextView) convertView.findViewById(R.id.lbl_terremoto);
        t_magnitud = (TextView) convertView.findViewById(R.id.lbl_magnitud);
        icono = (ImageView) convertView.findViewById(R.id.img_terremoto);

        t_terremoto.setText(item.getNombre());
        t_magnitud.setText(String.valueOf(item.getMagnitud()));

        if (item.getMagnitud()<=5){
            icono.setImageResource(R.drawable.earthquake2);
        } else if (item.getMagnitud()>5){
            icono.setImageResource(R.drawable.earthquake);
        }



        return convertView;
    }


}
