/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.converter;

import br.com.fbl.casa.eao.UsuarioEAO;
import br.com.fbl.casa.model.Usuario;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Faissal
 */
@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter, Serializable {
    
    private UsuarioEAO usuarioEAO;
    
    public UsuarioConverter() {
        usuarioEAO = new UsuarioEAO();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        usuarioEAO.getUsuarioExample().setNome(value);
        return usuarioEAO.getResultList().get(0);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return null;
        return ((Usuario)value).getNome();
    }
    
}
