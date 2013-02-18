/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.converter;

import br.com.fbl.casa.manager.TipoDespesaMB;
import br.com.fbl.casa.model.TipoDespesa;
import javax.el.ELContext;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Faissal
 */
@FacesConverter(value = "tipoDespesaConverter")
public class TipoDespesaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TipoDespesa tp = null;
        if (value != null || value.isEmpty()) {
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            tp = ((TipoDespesaMB) elContext.getELResolver().getValue(elContext, null, "tipoDespesaMB")).find(Long.valueOf(value));
        }
        return tp;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value != null ? ((TipoDespesa) value).getId().toString() : "";
    }
    
}
