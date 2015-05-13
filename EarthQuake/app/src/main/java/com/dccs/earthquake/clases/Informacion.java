package com.dccs.earthquake.clases;

import java.io.Serializable;

/**
 * Created by androidm on 12/05/2015.
 */
public class Informacion implements Serializable {
    private String nombre;

    public Informacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /*
    Si no tuviesemos constructor podriamos usar esto para guardar el nombre:
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     */

}
