package com.dccs.earthquake.vistas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dccs.earthquake.R;


public class SetupActivity extends ActionBarActivity {

    private Spinner actualiza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        iniComponentes();
    }

    //Inicializamos componentes, en este caso el spinner
    private void iniComponentes(){

        actualiza = (Spinner) findViewById(R.id.sp_timer);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tiempos,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actualiza.setAdapter(adapter);

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

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
