/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author equintana
 */
@Entity
@Table(name = "reservas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservas.findAll", query = "SELECT r FROM Reservas r"),
    @NamedQuery(name = "Reservas.findByIdcliente", query = "SELECT r FROM Reservas r WHERE r.reservasPK.idcliente = :idcliente"),
    @NamedQuery(name = "Reservas.findByIdevento", query = "SELECT r FROM Reservas r WHERE r.reservasPK.idevento = :idevento"),
    @NamedQuery(name = "Reservas.findByNumeroentradas", query = "SELECT r FROM Reservas r WHERE r.numeroentradas = :numeroentradas")})
public class Reservas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservasPK reservasPK;
    @Column(name = "numeroentradas")
    private Integer numeroentradas;

    public Reservas() {
    }

    public Reservas(ReservasPK reservasPK) {
        this.reservasPK = reservasPK;
    }

    public Reservas(Integer idcliente, Integer idevento) {
        this.reservasPK = new ReservasPK(idcliente, idevento);
    }

    public ReservasPK getReservasPK() {
        return reservasPK;
    }

    public void setReservasPK(ReservasPK reservasPK) {
        this.reservasPK = reservasPK;
    }

    public Integer getNumeroentradas() {
        return numeroentradas;
    }

    public void setNumeroentradas(Integer numeroentradas) {
        this.numeroentradas = numeroentradas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservasPK != null ? reservasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservas)) {
            return false;
        }
        Reservas other = (Reservas) object;
        if ((this.reservasPK == null && other.reservasPK != null) || (this.reservasPK != null && !this.reservasPK.equals(other.reservasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Reservas[ reservasPK=" + reservasPK + " ]";
    }
    
}
