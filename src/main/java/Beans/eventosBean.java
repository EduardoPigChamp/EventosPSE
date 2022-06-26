/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Eventos;
import Services.EventosFacadeREST;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author equintana
 */
@Named
@RequestScoped
public class eventosBEAN {
    
    @EJB
    final EventosFacadeREST service = new EventosFacadeREST();
    
    private String nombre;
    private String ciudad;
    private String estadio;
    private Date fecha;
    private Integer entradasTotales;
    private Integer entradasDisponibles;
    private Integer organizador;
    private Integer identifier;

    public List<Eventos> getEventos()
    {
        return service.futureEvents(new Date());
    }
}
