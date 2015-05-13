package com.dccs.earthquake.vistas;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.dccs.earthquake.R;




public class SetupActivity extends ActionBarActivity {

    private Spinner actualiza;
    private EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        iniComponentes();
    }

    //Inicializamos componentes, en este caso el spinner
    private void iniComponentes(){

        //Cargamos Spinner con los valores de refresco
        actualiza = (Spinner) findViewById(R.id.sp_timer);
        ArrayAdapter<CharSequence> datos = ArrayAdapter.createFromResource(this,R.array.tiempos,android.R.layout.simple_spinner_item);
        datos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actualiza.setAdapter(datos);

        url = (EditText) findViewById(R.id.txt_feed);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setup, menu);
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
            //Guardamos frecuencia de actualización.
            //Cogemos la frecuencia del spinner
            String frecuencia= actualiza.getSelectedItem().toString();
            //Creamos variable en Modo Privado
            SharedPreferences guardarDatos = getSharedPreferences("datos", Context.MODE_PRIVATE);
            //Abrimos editor de Preferencias
            Editor editor= guardarDatos.edit();
            //Añadimos el valor de la frecuencia
            editor.putString("Frecuencia",frecuencia);
            //Añadimos la URL del Feed
            editor.putString("URL", url.getText().toString());
            editor.apply();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
