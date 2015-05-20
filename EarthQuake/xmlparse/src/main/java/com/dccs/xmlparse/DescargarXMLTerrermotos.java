package com.dccs.xmlparse;

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
 * Created by androidm on 20/05/2015.
 */
public class DescargarXMLTerrermotos extends AsyncTask<String, Void, List<Terremoto>> {

    private ArrayAdapter<Terremoto> adapter;

    public DescargarXMLTerrermotos(ArrayAdapter<Terremoto> adapter) {
        this.adapter = adapter;
    }

    @Override
    protected List<Terremoto> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);

            URLConnection con = url.openConnection();

            InputStream is = con.getInputStream();

            return parsearXML(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Terremoto> terremotos) {
        super.onPostExecute(terremotos);

        adapter.addAll(terremotos);
        adapter.notifyDataSetChanged();
    }

    private List<Terremoto> parsearXML(InputStream is) throws XmlPullParserException, IOException {

        LinkedList<Terremoto> resultado = new LinkedList<>();

        XmlPullParser parser = Xml.newPullParser();

        parser.setInput(is,"UTF-8");

        int event = parser.getEventType();

        Terremoto terremoto = null;

        while(event != XmlPullParser.END_DOCUMENT){
            String tag = parser.getName();

            if(event == XmlPullParser.START_TAG) {
                if (tag.equals("entry")) {
                    terremoto = new Terremoto();
                } else if(terremoto != null){
                    if(tag.equals("id")){
                        terremoto.setId(parser.nextText());
                    } else if(tag.equals("title")){
                        String s = parser.nextText();

                        String[] split = s.split(" - ");

                        float magnitud = Float.parseFloat(split[0].split(" ")[1]);
                        String titulo = split[1];

                        terremoto.setTitulo(titulo);
                        terremoto.setMagnitud(magnitud);

                    } else if(tag.equals("updated")){
                        //2015-05-20T08:17:54.817Z
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        try {
                            Date date = sdf.parse(parser.nextText());
                            terremoto.setFecha(date);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    } else if(tag.equals("link")){
                        String link = parser.getAttributeValue(null, "href");
                        terremoto.setLink(link);
                    } else if(tag.equals("point")){
                        String[] coordenadas = parser.nextText().split(" ");
                        terremoto.setLatitud(Double.parseDouble(coordenadas[0]));
                        terremoto.setLongitud(Double.parseDouble(coordenadas[1]));
                    }
                }
            }else if (event == XmlPullParser.END_TAG){
                if (tag.equals("entry")) {
                    resultado.add(terremoto);
                    terremoto = null;
                }
            }

            event = parser.next();
        }

        return resultado;
    }
}
