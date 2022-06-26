package Services;

import Entities.Eventos;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
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

/**
 *
 * @author equintana
 */

@Stateless
@Path("entities.eventos")
public class EventosFacadeREST extends AbstractFacade<Eventos> {

    @PersistenceContext(unitName = "com.mycompany_EventosPSE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EventosFacadeREST() {
        super(Eventos.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Eventos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Eventos entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Eventos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Eventos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Eventos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("future_events/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Eventos> futureEvents(@PathParam("date") Date date)
    {
        return em.
            createQuery("SELECT c FROM Eventos c WHERE c.fecha >= :date").
            setParameter("date", date).
            getResultList();
    }
    
    @GET
    @Path("organizedEvents/{idOrganicer}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Eventos> eventsByOrganizer(@PathParam("idOrganicer") Integer idOrganicer)
    {
        return em.
            createQuery("SELECT c FROM Eventos c WHERE c.organizador = :idOrganicer").
            setParameter("idOrganicer", idOrganicer).
            getResultList();
    }
    
    @GET
    @Path("max")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer maxId() {
        Integer res = (Integer)em.createQuery("SELECT MAX(u.identifier) FROM Eventos u").getSingleResult();
        if(res == null) res = 0;
        return res;
    }
    
    @GET
    @Path("entradasDisponibles/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer entradasDisponibles(@PathParam("id") Integer id) {
        Integer res = (Integer)em.
                createQuery("SELECT u.entradasDisponibles FROM Eventos u WHERE u.identifier = :id").
                setParameter("id", id).
                getSingleResult();
        return res;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
