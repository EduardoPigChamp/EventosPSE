/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author equintana
 */
@Named
@RequestScoped 
public class LocationBEAN {
    private Integer id;
    private String nombre_estadio;
    private Date fecha;
    private boolean disponibilidad;

    //Parametros JSON
    public static String ID = "id";
    public static String NOMBRE_ESTADIO = "nombre_estadio";
    public static String FECHA = "fecha";
    public static String DISPONIBILIDAD = "libre";
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_estadio() {
        return nombre_estadio;
    }

    public void setNombre_estadio(String nombre_estadio) {
        this.nombre_estadio = nombre_estadio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}
