package com.dccs.earthquake.vistas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dccs.earthquake.R;
import com.dccs.earthquake.fragments.AlertDialogFragment;


public class SetupActivity extends ActionBarActivity {

    private Spinner actualiza;
    private EditText url;
    AlertDialogFragment alerta = new AlertDialogFragment();
    //DatosTerremoto datosDescargados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        iniComponentes();

    }

    //Inicializamos componentes, en este caso el spinner
    private void iniComponentes() {

        //Cargamos Spinner con los valores de refresco
        actualiza = (Spinner) findViewById(R.id.sp_timer);
        ArrayAdapter<CharSequence> datos = ArrayAdapter.createFromResource(this, R.array.tiempos, android.R.layout.simple_spinner_item);
        datos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actualiza.setAdapter(datos);

        url = (EditText) findViewById(R.id.txt_feed);

        SharedPreferences preferencias = getSharedPreferences("Preferencias EarthQuake",MODE_PRIVATE);
        url.setText(preferencias.getString("URL", "No definida"));

        actualiza.setSelection(preferencias.getInt("Frecuencia",0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mnu_setup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnu_save) {
            alerta.setPersonalMessage("Desea guardar los cambios?");
            alerta.setOnClickListenerSi(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Guardamos frecuencia de actualizacion.
                    //Cogemos la frecuencia del spinner
                    int frecuencia = actualiza.getSelectedItemPosition();
                    //Creamos variable en Modo Privado
                    SharedPreferences guardarDatos = getSharedPreferences("Preferencias EarthQuake", Context.MODE_PRIVATE);
                    //Abrimos editor de Preferencias
                    Editor editor = guardarDatos.edit();
                    //Anyadimos el valor de la frecuencia
                    editor.putInt("Frecuencia", frecuencia);
                    //Anyadimos la URL del Feed
                    editor.putString("URL", url.getText().toString());
                    editor.apply();
                    Toast.makeText(SetupActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                }
            });
            alerta.setOnClickListenerNo(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(SetupActivity.this, "Datos NO guardados", Toast.LENGTH_SHORT).show();
                }
            });
            alerta.show(getFragmentManager(), "Aviso");
        } else if (id == R.id.mnu_sync) {


        }

        return super.onOptionsItemSelected(item);
    }
}
