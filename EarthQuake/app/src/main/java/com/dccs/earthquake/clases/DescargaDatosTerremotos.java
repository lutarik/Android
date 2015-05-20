package com.dccs.earthquake.clases;

import android.os.AsyncTask;
import android.util.Xml;
import android.widget.ArrayAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by androidm on 19/05/2015.
 */

/**
 * PARA TRASFORMAR XML
 * SAX: Xml tal cual.
 * DOM: Objeto Java Document. Crea un nodo para cada dato. Se suele usar cdo no se necesite todos los datos del documento sino busqueda de un dato que este dentro del doc
 * XMLPullParse: El que vamos a utilizar.
 */

public class DescargaDatosTerremotos extends AsyncTask<String, Void, List<DatosTerremoto>> {

    private ArrayAdapter<DatosTerremoto> terremotosAD;

    public DescargaDatosTerremotos(ArrayAdapter<DatosTerremoto> terremotos) {
        terremotosAD = terremotos;
    }

    @Override
    protected List<DatosTerremoto> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            URLConnection con = url.openConnection();
            InputStream receptor = con.getInputStream();

            return parsearXML(receptor);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<DatosTerremoto> parsearXML(InputStream receptor) throws XmlPullParserException, IOException {

        LinkedList<DatosTerremoto> terremotos = new LinkedList<>();
        DatosTerremoto itemTerremoto = null;

        XmlPullParser parser = Xml.newPullParser();

        parser.setInput(receptor, "UTF-8");

        int evento = parser.getEventType();

        //Mientras no lleguemos a END_DOCUMENT recorremos el XML
        while (evento != XmlPullParser.END_DOCUMENT) {
            String nombreTag = parser.getName();
            //Si nos encontramos el TAG de inicio de datos
            if (evento == XmlPullParser.START_TAG) {
                if (nombreTag.equals("entry")) {
                    itemTerremoto = new DatosTerremoto();
                } else if (itemTerremoto != null) { //Si se ha generado itemTerremoto
                    if (nombreTag.equals("id")){ //Guardamos el id
                        itemTerremoto.setId(parser.nextText());
                    }else if (nombreTag.equals("title")){ //Guardamos el titulo y la magnitud
                        String titulo = parser.nextText();
                        String[] txt = titulo.split(" - ");

                        float magnitud = Float.parseFloat(txt[0].split(" ")[1]);
                        titulo = txt[1];

                        itemTerremoto.setMagnitud(magnitud);
                        itemTerremoto.setTitulo(titulo);

                    }else if (nombreTag.equals("updated")){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        try {
                            Date fecha = sdf.parse(parser.nextText());
                            itemTerremoto.setFecha(fecha);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else if (nombreTag.equals("link")){
                        String link = parser.getAttributeValue(null, "href");
                        itemTerremoto.setLink(link);
                    }else if (nombreTag.equals("point")){

                        String[] coordenadas = parser.nextText().split(" ");

                        itemTerremoto.setLatitud(Double.parseDouble(coordenadas[0]));
                        itemTerremoto.setLongitud(Double.parseDouble(coordenadas[1]));
                    }
                }
            } else if (evento != XmlPullParser.END_TAG) {
                if (nombreTag.equals("entry")) {
                    terremotos.add(itemTerremoto);
                    itemTerremoto = null;
                }
            }

            evento = parser.next();
        }
        return terremotos;
    }

    @Override
    protected void onPostExecute(List<DatosTerremoto> datosTerremotos) {
        super.onPostExecute(datosTerremotos);

        terremotosAD.addAll(datosTerremotos);
        terremotosAD.notifyDataSetChanged();
    }
}
