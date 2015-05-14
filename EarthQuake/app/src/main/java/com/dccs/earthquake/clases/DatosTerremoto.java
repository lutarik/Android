package com.dccs.earthquake.clases;

import java.util.Date;

/**
 * Created by androidm on 13/05/2015.
 */
public class DatosTerremoto {

    /*
    Guardamos los datos del terremoto, sirve tanto para filtrar como para mostrar datos
     */
    private String fecha;
    private String nombre;
    private float magnitud;

    public DatosTerremoto(String fecha, String nombre, float magnitud) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.magnitud = magnitud;
    }

    public DatosTerremoto() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(float magnitud) {
        this.magnitud = magnitud;
    }
}
