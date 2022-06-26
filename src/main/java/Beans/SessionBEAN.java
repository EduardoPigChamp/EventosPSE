package Beans;

import Entities.Usuario;
import Services.UsuarioFacadeREST;
import java.util.Date;
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
public class sessionBEAN {
    
    @EJB
    final UsuarioFacadeREST service = new UsuarioFacadeREST();
    
    private Integer admin;
    private int identifier;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private Date fechaNacimiento;
    private String telefono;
    private Integer organizator;
    private String username;
    private String password;
    
    public sessionBEAN()
    {
        if(checkSession())
        {
            admin = this.getSession().getAdminn();
            identifier = this.getSession().getIdentifier();
            nombre = this.getSession().getNombre();
            apellido1 = this.getSession().getApellido1();
            apellido2 = this.getSession().getApellido2();
            dni = this.getSession().getDni();
            fechaNacimiento = this.getSession().getFechaNacimiento();
            telefono = this.getSession().getTelefono();
            organizator = this.getSession().getOrganizator();
            username = this.getSession().getUserName();
            password = "###################";
        }
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getOrganizator() {
        return organizator;
    }

    public void setOrganizator(Integer organizator) {
        this.organizator = organizator;
    }
    
    
    
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
    
    private String createSession(Usuario user)
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("LoguedUser", user);
        return "/index";
    }
    
    public boolean checkSession()
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("LoguedUser");
    }
    
    public String closeSession()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        return "/index";
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
    
    public void updateUser()
    {
        Usuario user = new Usuario();
        
        user.setAdminn(admin);
        user.setApellido1(apellido1);
        user.setApellido2(apellido2);
        user.setDni(dni);
        user.setFechaNacimiento(fechaNacimiento);
        user.setNombre(nombre);
        user.setOrganizator(organizator);
        user.setPass(password.hashCode());
        user.setTelefono(telefono);
        user.setUserName(username);
        user.setIdentifier(identifier);
        
        try{
            service.edit(user);
            closeSession();
            createSession(user);
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ha producido un error actualizando el usuario, intentelo de nuevo"));
        }
    }
    
    public String dropUser()
    {
        String ret = null;
        
        try
        {
            service.remove(getSession());
            closeSession();
            ret = "/index";
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problemas al intentar borrar el usuario, por favor intentelo de nuevo"));
        }
        
        return ret;
    }
}
