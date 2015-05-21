package com.dccs.earthquake.clases;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by androidm on 21/05/2015.
 */
public class FiltroBusquedaDTO implements Serializable{
    private Integer magnitud;
    private Date fecha;

    public FiltroBusquedaDTO(Integer magnitud, Date fecha) {
        this.magnitud = magnitud;
        this.fecha = fecha;
    }

    public Integer getMagnitud() {
        return magnitud;
    }

    public Date getFecha() {
        return fecha;
    }
}
