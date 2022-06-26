/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Eventos;
import Entities.Reservas;
import Services.EventosFacadeREST;
import Services.ReservasFacadeREST;
import java.io.Serializable;
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
@FlowScoped("comprarEntrada")
public class comprarEntrada implements Serializable{
    
    @EJB
    final ReservasFacadeREST serviceReservas = new ReservasFacadeREST();
    @EJB
    final EventosFacadeREST serviceEventos = new EventosFacadeREST();
    
    private Integer idCliente, idEvento, numEntradas;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(Integer numEntradas) {
        this.numEntradas = numEntradas;
    }
    
    public String buyAndShow()
    {
        try
        {
            Eventos event = serviceEventos.find(this.idEvento);
            event.setEntradasDisponibles(event.getEntradasDisponibles() - numEntradas);
            Reservas res = new Reservas(new sessionBEAN().getIdentifier(), idEvento);
            res.setNumeroentradas(numEntradas);
            serviceReservas.create(res);
            serviceEventos.edit(event);
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ya posee entradas para este evento"));
            return null;
        }
        
        return "mostrarQR";
    }
    
    @Override
    public String toString()
    {
        return "{idCliente: '" + idCliente + "', "
                + "idEvento: '" + idEvento + "', "
                + "numEntradas: " + numEntradas + "}";
    }
    
}
