package com.dccs.earthquake.clases;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Desacargamos los datos de los terremotos
 */

public class DescargaDatosTerremotosAsync extends AsyncTask<String, Integer, DatosTerremoto> {

    DatosTerremoto datos;

    public DescargaDatosTerremotosAsync(DatosTerremoto datos) {
        this.datos = datos;
    }

    //Realiza las acciones del hilo
    @Override
    protected DatosTerremoto doInBackground(String... params) {
        try {
            //Creamos la conexion
            URL url = new URL(params[0]);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            InputStream datos = conexion.getInputStream();
            byte[] buffer = new byte[512];
            //Creamos un array con el tamanyo del archivo que nos vamos a descargar
            byte[] xml = new byte[conexion.getContentLength()];

            for(int bytesTotalesLeidos = 0;bytesTotalesLeidos< conexion.getContentLength();){

                int datos_leidos = datos.read(buffer);

                System.arraycopy(buffer,0, conexion.getContentLength(),bytesTotalesLeidos,datos_leidos);
                bytesTotalesLeidos+=datos_leidos;

                publishProgress(bytesTotalesLeidos, conexion.getContentLength());
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Procesa despues de ejecutar
    @Override
    protected void onPostExecute(DatosTerremoto datosTerremoto) {
        super.onPostExecute(datosTerremoto);
    }

    //Muestra el progreso del hilo
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);


    }

    //Lo que se va a ejecutar en el hilo principal antes de que se ejecute el secundario
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}
