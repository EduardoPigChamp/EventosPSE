/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author equintana
 */
@Entity
@Table(name = "eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventos.findAll", query = "SELECT e FROM Eventos e"),
    @NamedQuery(name = "Eventos.findByNombre", query = "SELECT e FROM Eventos e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Eventos.findByCiudad", query = "SELECT e FROM Eventos e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "Eventos.findByEstadio", query = "SELECT e FROM Eventos e WHERE e.estadio = :estadio"),
    @NamedQuery(name = "Eventos.findByFecha", query = "SELECT e FROM Eventos e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "Eventos.findByEntradasTotales", query = "SELECT e FROM Eventos e WHERE e.entradasTotales = :entradasTotales"),
    @NamedQuery(name = "Eventos.findByEntradasDisponibles", query = "SELECT e FROM Eventos e WHERE e.entradasDisponibles = :entradasDisponibles"),
    @NamedQuery(name = "Eventos.findByOrganizador", query = "SELECT e FROM Eventos e WHERE e.organizador = :organizador"),
    @NamedQuery(name = "Eventos.findByIdentifier", query = "SELECT e FROM Eventos e WHERE e.identifier = :identifier")})
public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 2147483647)
    @Column(name = "estadio")
    private String estadio;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "entradas_totales")
    private Integer entradasTotales;
    @Column(name = "entradas_disponibles")
    private Integer entradasDisponibles;
    @Column(name = "organizador")
    private Integer organizador;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identifier")
    private Integer identifier;

    public Eventos() {
    }

    public Eventos(Integer identifier) {
        this.identifier = identifier;
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

    public Integer getEntradasTotales() {
        return entradasTotales;
    }

    public void setEntradasTotales(Integer entradasTotales) {
        this.entradasTotales = entradasTotales;
    }

    public Integer getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(Integer entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }

    public Integer getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Integer organizador) {
        this.organizador = organizador;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identifier != null ? identifier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventos)) {
            return false;
        }
        Eventos other = (Eventos) object;
        if ((this.identifier == null && other.identifier != null) || (this.identifier != null && !this.identifier.equals(other.identifier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.eventospse.Eventos[ identifier=" + identifier + " ]";
    }
    
}
