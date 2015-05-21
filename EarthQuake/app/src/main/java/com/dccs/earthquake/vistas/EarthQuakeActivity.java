package com.dccs.earthquake.vistas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.dccs.earthquake.R;
import com.dccs.earthquake.clases.DatosTerremoto;
import com.dccs.earthquake.clases.FiltroBusquedaDTO;
import com.dccs.earthquake.clases.Informacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class EarthQuakeActivity extends ActionBarActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Spinner sp_mag;
    Button b_busqueda;
    TextView t_fecha;
    private Calendar c = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        iniComponentes();
    }

    private void iniComponentes() {

        //Cargamos Spinner con las magnitudes
        sp_mag = (Spinner) findViewById(R.id.sp_magnitud);
        ArrayAdapter<CharSequence> datos = ArrayAdapter.createFromResource(this, R.array.magnitudes, android.R.layout.simple_spinner_item);
        datos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_mag.setAdapter(datos);
        //Creamos el boton
        b_busqueda = (Button) findViewById(R.id.btn_search);
        b_busqueda.setOnClickListener(this);
        //Creamos campo fecha
        t_fecha = (TextView) findViewById(R.id.txt_fecha);
        t_fecha.setOnClickListener(this);
        updateLabel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mnu_ppal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.mnu_settings) {
            //Llamamos a la vista Setup
            Intent setup = new Intent(this, SetupActivity.class);
            startActivity(setup);
            return true;

        } else if (id == R.id.mnu_help) {
            Intent ayuda = new Intent(this, HelpActivity.class);
            //Variable serializable
            Informacion dato = new Informacion("Luis Lorca");
            //Toast.makeText(this,dato.getNombre().toString(),Toast.LENGTH_SHORT).show();
            ayuda.putExtra("dato", dato);

            //Se lanza la actividad sin esperar respuesta
            startActivity(ayuda);

            /*
            *startActivityForResult(ayuda,0);
            *Se lanza la actividad y espera respuesta. ayuda = Intent y 0 = al id que nosotros queramos asignarle
            */

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View campo) {

        if (campo.getId() == R.id.btn_search) {

            FiltroBusquedaDTO itemFiltro = new FiltroBusquedaDTO(Integer.parseInt(sp_mag.getSelectedItem().toString()), t_fecha.getText().toString())

            Intent busqueda = new Intent(this, BusquedaActivity.class);

            //item_busqueda.setFecha(t_fecha.getText().toString());
            item_busqueda.setMagnitud(Integer.parseInt(sp_mag.getSelectedItem().toString()));

            busqueda.putExtra("busqueda", item_busqueda);
            startActivity(busqueda);
        } else if (campo.getId() == R.id.txt_fecha) {
            mostrarFecha();
        }

    }

    private void mostrarFecha() {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    }

    private void updateLabel() {
        Date date = c.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        t_fecha.setText(format.format(date));
    }



/*    //Recogemos la devolucion de los lanzamientos de las actividades con el metodo startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        *//*TODO:
        *AQUI HAREMOS TANTOS IF ELSE COMO startActivityForResult QUE HAYAMOS LANZADO
        * COMPROBANDO EL ID QUE NOS DEVUELVE MEDIANTE LA VARIABLE requestCode.
        *//*
        if (requestCode == 1) {
            //para obtener los valores devueltos por la Actividad finalizada
            String resultado = data.getExtras().getString("datos");
            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
        }
    }
    */
}
