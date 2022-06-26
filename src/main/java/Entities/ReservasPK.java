/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author equintana
 */
@Embeddable
public class ReservasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idcliente")
    private Integer idcliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idevento")
    private Integer idevento;

    public ReservasPK() {
    }

    public ReservasPK(Integer idcliente, Integer idevento) {
        this.idcliente = idcliente;
        this.idevento = idevento;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcliente;
        hash += (int) idevento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservasPK)) {
            return false;
        }
        ReservasPK other = (ReservasPK) object;
        if (this.idcliente != other.idcliente) {
            return false;
        }
        if (this.idevento != other.idevento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ReservasPK[ idcliente=" + idcliente + ", idevento=" + idevento + " ]";
    }
    
}
