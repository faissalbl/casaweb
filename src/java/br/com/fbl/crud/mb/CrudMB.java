/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.crud.mb;

import br.com.fbl.crud.facade.CrudFacadeLocal;
import br.com.fbl.crud.model.GenericEntity;
import br.com.fbl.crud.facade.persistence.exception.PersistenceRollbackException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Faissal
 */
public abstract class CrudMB<T extends GenericEntity> implements Serializable {
    
    protected static final String ID_MES_REFERENCIA_KEY = "idMesReferencia";
    
    @EJB
    private CrudFacadeLocal crudFacade;
    
    private T model;
    private Collection<T> modelList;
    private Map<String, Object> queryParams;
    
    public T find(Object id) {
        return crudFacade.find((Class<T>)getModel().getClass(), id);
    }
    
    /**
     * Find an entity of any type specified.
     * @param clazz class of the entity which is the type of the result
     * @param id id of the entity to be found
     * @return the entity of the type specified with the id specified
     */
    public <E extends GenericEntity> E find(Class<E> clazz, Object id) {
        return crudFacade.find(clazz, id);
    }
    
    /**
     * Finds all entities constrained by the <code>queryParams</code> content.
     */
    public Collection<T> findAll() {
        return crudFacade.findAll((Class<T>)getModel().getClass(), getQueryParams());
    }
    
    public <E extends GenericEntity> Collection<E> findAll(Class<E> clazz, Map<String, Object> queryParams) {
        return crudFacade.findAll(clazz, queryParams);
    }

    public String save() {
        crudFacade.save(getModel());
        return "success";
    }
    
    public void remove(Long idEntity) {
        try {
            crudFacade.remove(getModel().getClass(), idEntity);
            /* necessario para que o sistema busque de novo os registros */
            setModelList(null);
        } catch (PersistenceRollbackException ex) {
            Logger.getLogger(CrudMB.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
    }
    
    public T getModel() {
        if (model == null) {
            model = getInstance();
        }
        return model;
    }
    
    protected abstract T getInstance();
    
    public void setModel(T model) {
        this.model = model;
    }
    
    public Collection<T> getModelList() {
        if (modelList == null) {
            modelList = findAll();
        }
        return modelList;
    }

    public void setModelList(Collection<T> modelList) {
        this.modelList = modelList;
    }

    public Map<String, Object> getQueryParams() {
        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }    
    
}
