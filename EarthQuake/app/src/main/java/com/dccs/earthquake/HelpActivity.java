package com.dccs.earthquake;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class HelpActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Cogemos la Actividad que nos ha llamado y cogemos lo que nos ha pasado con putExtra
        Informacion dato = (Informacion) getIntent().getExtras().getSerializable("datos");
        dato.getNombre();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mnu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {

            //para en el caso en el que se haya llamado a la actividad con el startActivityForResult guardaremos los datos con la siguiente linea
            Intent devolucion=new Intent(); //Generamos una nueva Intención
            devolucion.putExtra("resultado","El resultado es bueno"); // Añadimos el valor resultado
            setResult(Activity.RESULT_OK, devolucion); // Indicamos que el resultado ha sido ok y mandamos la intención "devolucion".

            //Terminamos la actividad
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
