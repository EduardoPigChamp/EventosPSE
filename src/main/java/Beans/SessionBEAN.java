package Beans;

import Entities.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author equintana
 */

@Named
@RequestScoped       
public class SessionBEAN {
    
    public void createSession(Usuario user)
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("LoguedUser", user);
    }
    
    public boolean checkSession()
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("LoguedUser");
    }
    
    public void closeSession()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
    }
    
    //TODO
    private Usuario getSession()
    {
        return null;
    }
}
