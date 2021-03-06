package com.dccs.earthquake.clases;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DccS
 */
public class DatosTerremoto implements Serializable {

    /*
    Guardamos los datos del terremoto, sirve tanto para filtrar como para mostrar datos
     */
    private String id;
    private String titulo;
    private Date fecha;
    private String link;
    private double latitud;
    private double longitud;
    private float magnitud;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosTerremoto that = (DatosTerremoto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DatosTerremoto{" +
                "titulo='" + titulo + '\'' +
                ", magnitud=" + magnitud +
                ", fecha=" + fecha +
                '}';
    }

    public DatosTerremoto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public float getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(float magnitud) {
        this.magnitud = magnitud;
    }
}
