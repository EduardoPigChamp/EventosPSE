/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reservas;
import Entities.ReservasPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author equintana
 */
@Stateless
@Path("entities.reservas")
public class ReservasFacadeREST extends AbstractFacade<Reservas> {

    @PersistenceContext(unitName = "com.mycompany_EventosPSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private ReservasPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idcliente=idclienteValue;idevento=ideventoValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        Entities.ReservasPK key = new Entities.ReservasPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idcliente = map.get("idcliente");
        if (idcliente != null && !idcliente.isEmpty()) {
            key.setIdcliente(new java.lang.Integer(idcliente.get(0)));
        }
        java.util.List<String> idevento = map.get("idevento");
        if (idevento != null && !idevento.isEmpty()) {
            key.setIdevento(new java.lang.Integer(idevento.get(0)));
        }
        return key;
    }

    public ReservasFacadeREST() {
        super(Reservas.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Reservas entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Reservas entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        Entities.ReservasPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }
    
    @DELETE
    @Path("removeClient/{idCliente}")
    public void removeByClient(@PathParam("idCliente") PathSegment idClient) {
        em.createQuery("DELETE FROM Reservas c WHERE c.reservasPK.idcliente = :idClient").setParameter("idCliente", idClient).executeUpdate();
    }
    
    @DELETE
    @Path("removeOrganizator/{idOrganizator}")
    public void removeByOrganizator(@PathParam("idOrganizator") PathSegment idOrganizator) {
        em.createQuery("DELETE FROM Reservas c WHERE c.reservasPK.idcliente = :idOrganizator").setParameter("idOrganizator", idOrganizator).executeUpdate();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Reservas find(@PathParam("id") PathSegment id) {
        Entities.ReservasPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Reservas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Reservas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
