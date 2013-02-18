/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Faissal
 */
@FacesConverter(value = "floatConverter")
public class FloatConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        BigDecimal convertedValue = null;
        if (value != null) {
            try {
                convertedValue = new BigDecimal(value.replaceAll("[.]", "").replaceAll("[,]", ".")).setScale(2, RoundingMode.HALF_EVEN);
            } catch (NumberFormatException e) {
            }
        }
        return convertedValue != null ? convertedValue.floatValue() : convertedValue;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String convertedValue = null;
        if (value != null && !value.toString().isEmpty())
            convertedValue = new BigDecimal(value.toString()).setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("[,]", "").replaceAll("[.]", ",");
        return convertedValue;
    }
    
}
