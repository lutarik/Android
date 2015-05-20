package com.dccs.earthquake.vistas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.dccs.earthquake.R;
import com.dccs.earthquake.clases.DatosTerremoto;

public class BusquedaDetalleActivity extends Activity {

    TextView t_fecha, t_titulo, t_mag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_detalle);

        DatosTerremoto terremoto = (DatosTerremoto) getIntent().getExtras().getSerializable("item");

        iniComponentes();

        t_titulo.setText(this.getString(R.string.terremoto) + " " + terremoto.getTitulo());
        t_fecha.setText(this.getString(R.string.fecha) + " " + terremoto.getFecha());
        t_mag.setText(this.getString(R.string.magnitud) + " " + String.valueOf(terremoto.getMagnitud()));

    }

    private void iniComponentes() {
        t_fecha = (TextView) findViewById(R.id.lbl_fecha);
        t_mag = (TextView) findViewById(R.id.lbl_mag);
        t_titulo = (TextView) findViewById(R.id.lbl_dterremoto);
    }
}
