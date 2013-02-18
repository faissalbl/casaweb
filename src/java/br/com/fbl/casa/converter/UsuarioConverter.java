/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.converter;

import br.com.fbl.casa.manager.UsuarioMB;
import br.com.fbl.casa.model.Usuario;
import javax.el.ELContext;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Faissal
 */
@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario u = null;
        if (value != null || value.isEmpty()) {
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            u = ((UsuarioMB) elContext.getELResolver().getValue(elContext, null, "usuarioMB")).find(Long.valueOf(value));
        }
        return u;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value != null ? ((Usuario) value).getId().toString() : "";
    }
    
}
