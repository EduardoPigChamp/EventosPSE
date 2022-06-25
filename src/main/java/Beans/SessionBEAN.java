package Beans;

import Entities.Usuario;
import Services.UsuarioFacadeREST;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author equintana
 */

@Named
@RequestScoped
public class SessionBEAN {
    
    @EJB
    UsuarioFacadeREST service = new UsuarioFacadeREST();
    
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void createSession()
    {
        Usuario user = service.find(username);
        
        if(user != null)
        {
            if(user.getPass() == password.hashCode())
            {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("LoguedUser", user);
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario no existe o la contraseña es incorrecta"));
            }
        }
    }
    
    public boolean checkSession()
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("LoguedUser");
    }
    
    public void closeSession()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
    }
        
    private Usuario getSession()
    {
        Usuario user = null;
        
        if(checkSession())
        {
            user = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoguedUser");
        }
        
        return user;
    }
    
    public boolean isAdmin()
    {
        Usuario user;
        
        if(checkSession())
        {
            user = getSession();
            return user.getAdminn() == 1;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isOrganizator()
    {
        Usuario user;
        
        if(checkSession())
        {
            user = getSession();
            return user.getOrganizator() == 1;
        }
        else
        {
            return false;
        }
    }
}
