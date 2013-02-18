/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Faissal
 */
@FacesValidator("mesValidator")
public class MesValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String errorMsg = null;
        try {
            int iValue = Integer.parseInt(value.toString());
            if (iValue < 1 || iValue > 12) {
                errorMsg = "O valor deve estar entre 1 e 12";
            }
        } catch (NumberFormatException e) {
            errorMsg = "O valor informado não é número";
        }
        if (errorMsg != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, ""));
        }
    }

}
