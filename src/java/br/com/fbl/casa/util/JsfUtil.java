package br.com.fbl.casa.util;

import javax.faces.context.FacesContext;

public class JsfUtil {
    
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
    
    public static Object getSessionProperty(String propertyName) {
        return FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap()
                    .get(propertyName);
    }
    
    public static void setSessionProperty(String propertyName, Object value) {
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .put(propertyName, value);
    }
    
    public static void setRequestProperty(String propertyName, Object value) {
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestMap()
                .put(propertyName, value);
    }

    public static Object getRequestProperty(String propertyName) {
        return FacesContext.getCurrentInstance()
                    .getExternalContext().getRequestMap()
                    .get(propertyName);
    }
    
}
