/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.converter;

import br.com.fbl.casa.eao.TipoDespesaEAO;
import br.com.fbl.casa.model.TipoDespesa;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Faissal
 */
@FacesConverter(forClass=TipoDespesa.class)
public class TipoDespesaConverter implements Converter, Serializable {
    
    private TipoDespesaEAO tipoDespesaEAO;
    
    public TipoDespesaConverter() {
        tipoDespesaEAO = new TipoDespesaEAO();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        tipoDespesaEAO.getTipoDespesaExample().setNome(value);
        return tipoDespesaEAO.getResultList().get(0);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return null;
        return ((TipoDespesa)value).getNome();
    }
    
}
