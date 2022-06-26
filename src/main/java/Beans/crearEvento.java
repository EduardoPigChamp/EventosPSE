package Beans;

import Entities.Eventos;
import Services.EventosFacadeREST;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author equintana
 */
@Named
@FlowScoped("crearEvento")
public class crearEvento implements Serializable{
    
    @EJB
    final EventosFacadeREST service = new EventosFacadeREST();
    
    Date fecha;
    String estadio, nombre, ciudad;
    int entradas_totales;

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

    public int getEntradas_totales() {
        return entradas_totales;
    }

    public void setEntradas_totales(int entradas_totales) {
        this.entradas_totales = entradas_totales;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String saveEvento()
    {
        Eventos event = new Eventos();
        sessionBEAN session = new sessionBEAN();
        
        event.setCiudad(ciudad);
        event.setEntradasDisponibles(entradas_totales);
        event.setEntradasTotales(entradas_totales);
        event.setEstadio(estadio);
        event.setFecha(fecha);
        event.setIdentifier(service.maxId() + 1);
        event.setNombre(nombre);
        event.setOrganizador(session.getIdentifier());
        
        try
        {
            service.create(event);
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        
        return "/index";
    }
    
    /*public List<LocationBEAN> getLocations()
    {
    
    }*/
}
