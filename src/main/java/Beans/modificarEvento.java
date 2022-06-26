/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Eventos;
import Services.EventosFacadeREST;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author equintana
 */

@Named
@FlowScoped("modificarEvento")
public class modificarEvento implements Serializable{
    
    @EJB
    final EventosFacadeREST service = new EventosFacadeREST();
    
    Date fecha;
    String estadio, nombre, ciudad;
    Integer entradas_totales, entradas_restantes, organizador, indentifier;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEntradas_totales() {
        return entradas_totales;
    }

    public void setEntradas_totales(Integer entradas_totales) {
        this.entradas_totales = entradas_totales;
    }

    public Integer getEntradas_restantes() {
        return entradas_restantes;
    }

    public void setEntradas_restantes(Integer entradas_restantes) {
        this.entradas_restantes = entradas_restantes;
    }

    public Integer getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Integer organizador) {
        this.organizador = organizador;
    }

    public Integer getIndentifier() {
        return indentifier;
    }

    public void setIndentifier(Integer indentifier) {
        this.indentifier = indentifier;
    }
    
    public String yRecargamos()
    {
        Eventos event = service.find(this.indentifier);
        
        this.ciudad = event.getCiudad();
        this.entradas_restantes = event.getEntradasDisponibles();
        this.entradas_totales = event.getEntradasTotales();
        this.fecha = event.getFecha();
        this.indentifier = event.getIdentifier();
        this.nombre = event.getNombre();
        this.organizador = event.getOrganizador();
        
        return "mostrarEvento";
    }
    
    public String actualizarEvento()
    {
        Eventos event = service.find(this.indentifier);
        
        event.setCiudad(ciudad);
        event.setEntradasDisponibles(this.entradas_totales - event.getEntradasTotales() + this.entradas_restantes);
        event.setEntradasTotales(entradas_totales);
        event.setNombre(nombre);
        
        service.edit(event);
        
        return "/index";
    }
    
}
