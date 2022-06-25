/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author equintana
 */
@FacesConverter("Converters.passwordConverter")
public class passwordConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null) throw new ConverterException("El campo contraseña no puede ser nulo");
        return value.hashCode();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return o.toString();
    }

    
    
}
