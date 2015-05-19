package com.dccs.earthquake.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dccs.earthquake.R;
import com.dccs.earthquake.clases.DatosTerremoto;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaDetalleFragment extends Fragment {

    TextView t_fecha, t_titulo, t_mag;

    public BusquedaDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frg_busqueda_detalle, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatosTerremoto item = (DatosTerremoto) getActivity().getIntent().getSerializableExtra("item");
        if (item!=null){
            actualizarDetalle(item);
        }

    }

    public void actualizarDetalle(DatosTerremoto item) {

        iniComponentes();

        t_titulo.setText(this.getString(R.string.terremoto) + " " + item.getNombre());
        t_fecha.setText(this.getString(R.string.fecha) + " " + item.getFecha());
        t_mag.setText(this.getString(R.string.magnitud) + " " + String.valueOf(item.getMagnitud()));
    }

    private void iniComponentes() {

        t_fecha = (TextView) getActivity().findViewById(R.id.lbl_fecha);
        t_mag = (TextView) getActivity().findViewById(R.id.lbl_mag);
        t_titulo = (TextView) getActivity().findViewById(R.id.lbl_dterremoto);

    }


}
