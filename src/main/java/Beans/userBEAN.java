/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Usuario;
import Services.UsuarioFacadeREST;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author equintana
 */

@Named
@RequestScoped    
public class userBEAN {
    
    @EJB
    final UsuarioFacadeREST service;
    
    private String userName;
    private String pass;
    private Integer adminn;
    private Integer identifier;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private Date fechaNacimiento;
    private String telefono;

    public userBEAN()
    {
        service = new UsuarioFacadeREST();
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getAdminn() {
        return adminn;
    }

    public void setAdminn(Integer adminn) {
        this.adminn = adminn;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
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
    
    public void registrar()
    {
        Usuario user = new Usuario();
        
        user.setAdminn(0);
        user.setApellido1(apellido1);
        user.setApellido2(apellido2);
        user.setDni(dni);
        user.setFechaNacimiento(fechaNacimiento);
        user.setNombre(nombre);
        user.setPass(pass.hashCode());
        user.setTelefono(telefono);
        user.setUserName(userName);
        
        try{
            service.create(user);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
