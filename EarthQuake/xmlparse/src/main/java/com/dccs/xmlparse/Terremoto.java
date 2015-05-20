package com.dccs.xmlparse;

import java.util.Date;

/**
 * Created by androidm on 20/05/2015.
 */
public class Terremoto {
    private String id;
    private String titulo;
    private Date fecha;
    private String link;
    private double latitud;
    private double longitud;
    private float magnitud;

    public Terremoto() {
    }

    @Override
    public String toString() {
        return "Terremoto{" +
                "titulo='" + titulo + '\'' +
                ", magnitud=" + magnitud +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terremoto terremoto = (Terremoto) o;

        if (id != null ? !id.equals(terremoto.id) : terremoto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Terremoto(String id, String titulo, Date fecha, String link, double latitud, double longitud, float magnitud) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.link = link;
        this.latitud = latitud;
        this.longitud = longitud;
        this.magnitud = magnitud;
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
