package Beans;

import Entities.Eventos;
import Services.EventosFacadeREST;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author equintana
 */
@Named
@RequestScoped
public class EventoBEAN {
    
    @EJB
    final EventosFacadeREST service = new EventosFacadeREST();
    
    Date fecha;
    String estadio, nombre, ciudad;
    Integer entradas_totales, entradas_restantes, organizador, indentifier = null;

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

    public int getEntradas_restantes() {
        return entradas_restantes;
    }

    public void setEntradas_restantes(int entradas_restantes) {
        this.entradas_restantes = entradas_restantes;
    }

    public int getOrganizador() {
        return organizador;
    }

    public void setOrganizador(int organizador) {
        this.organizador = organizador;
    }

    public Integer getIndentifier() {
        return indentifier;
    }

    public void setIndentifier(int indentifier) {
        this.indentifier = indentifier;
    }
    
    public List<Eventos> getFutureEventos()
    {
        return service.futureEvents(new Date());
    }
    
    public List<Eventos> getEventosByOrganicer(int idOrganicer)
    {
        return service.eventsByOrganizer(idOrganicer);
    }
    
    public String updateEvent()
    {
        Eventos event = new Eventos();
        
        event.setCiudad(ciudad);
        event.setEntradasDisponibles(entradas_restantes);
        event.setEntradasTotales(entradas_totales);
        event.setEstadio(estadio);
        event.setFecha(fecha);
        event.setNombre(nombre);
        event.setIdentifier(indentifier);
        event.setOrganizador(organizador);
        
        service.edit(event);
        
        return "/index";
    }
    
    public String deleteEventById(Integer id)
    {
        service.remove(id);
        return "/index";
    }
    
    public Integer getentradasDisponibles(Integer id)
    {
        return service.entradasDisponibles(id);
    }
}
