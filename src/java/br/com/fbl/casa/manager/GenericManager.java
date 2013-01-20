/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.eao.GenericEAO;
import br.com.fbl.casa.model.GenericEntity;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author Faissal
 */
public abstract class GenericManager<T extends GenericEntity> implements Serializable {
    
    protected abstract <E extends GenericEAO> E getEAO();
    
    public void remove(T entity) {
        getEAO().remove(entity);
    }    
    
    public String save(T entity) {
        String outcome;
        if (entity.getId() == null) {
            getEAO().persist(entity);
            outcome = "persisted";
        } else {
            entity = (T) getEAO().merge(entity);
            outcome = "persisted";
        }
        Object fromView = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("fromView");
        return fromView != null ? fromView.toString() : outcome;
    }    
    
    public List<? extends GenericEntity> findEntities() {
        return getEAO().getResultList();
    }
}
