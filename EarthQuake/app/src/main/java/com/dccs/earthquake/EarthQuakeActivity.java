package com.dccs.earthquake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class EarthQuakeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            return true;
        } else if (id == R.id.help) {
            Intent ayuda = new Intent(this, HelpActivity.class);
            //Variable serializable
            Informacion dato = new Informacion("Victor");

            ayuda.putExtra("dato", dato);

            /*
            *ayuda.putExtra("nombre_dato", valor_dato);
            *Con este método podemos pasarle a la Activity que cargamos los valores que queremos que tenga en cuenta. Se suela usar "serializable",
            *que es un proceso por el cual se pasa objetos a un determinado formato que represeenta al objeto
            */

            //Se lanza la actividad sin esperar respuesta
            startActivity(ayuda);

            /*
            *startActivityForResult(ayuda,0);
            *Se lanza la actividad y espera respuesta. ayuda = Intent y 0 = al id que nosotros queramos asignarle
            */

            return true;
        } else if (id == R.id.url) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //Recogemos la devolución de los lanzamientos de las actividades con el metodo startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*TODO:
        *AQUI HAREMOS TANTOS IF ELSE COMO startActivityForResult QUE HAYAMOS LANZADO
        * COMPROBANDO EL ID QUE NOS DEVUELVE MEDIANTE LA VARIABLE requestCode.
        */
        if (requestCode==1){
            //para obtener los valores devueltos por la Actividad finalizada
            String resultado = data.getExtras().getString("datos");
            Toast.makeText(this, resultado,Toast.LENGTH_SHORT).show();
        }
    }
}
