/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.GenericEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Faissal
 */
//FIXME habilitar JTA Transactions
//FIXME passar toda lógica de negócio e dados para uma outra camada. Preferencialmente EJB.
@ViewScoped
public abstract class CrudMB<T extends GenericEntity> implements Serializable {
    
    protected static final String ID_MES_REFERENCIA_KEY = "idMesReferencia";
    
    protected EntityManager entityManager;
    
    private T model;
    private Collection<T> modelList;
    private Map<String, Object> queryParams;
    
    public T find(Object id) {
        return entityManager.find((Class<T>)getModel().getClass(), id);
    }
    
    /**
     * Find an entity of any type specified.
     * @param clazz class of the entity which is the type of the result
     * @param id id of the entity to be found
     * @return the entity of the type specified with the id specified
     */
    public <E extends GenericEntity> E find(Class<E> clazz, Object id) {
        return entityManager.find(clazz, id);
    }
    
    public T refresh(T entity) {
        entityManager.refresh(entity);
        return entity;
    }
    
    /**
     * Refresh an entity of any type specified.
     * @param entity entity to be refreshed
     * @return the refreshed entity
     */
    public <E extends GenericEntity> E refreshOtherEntity(E entity) {
        entityManager.refresh(entity);
        return entity;
    }
    
    /**
     * Finds all entities constrained by the <code>queryParams</code> content.
     */
    public Collection<T> findAll() {
        return findAll((Class<T>)getModel().getClass());
    }

    /**
     * Finds all entities constrained by the <code>queryParams</code> content.
     * @param clazz The type of entity to find.
     */
    public <E extends GenericEntity> Collection<E> findAll(Class<E> clazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(clazz);
        Root<E> root = cq.from(clazz);
        cq.select(root);
        if (!getQueryParams().isEmpty()) {
            for (String paramKey: queryParams.keySet()) {
                cq.where(cb.equal(root.get(paramKey), queryParams.get(paramKey)));
            }
        }
        TypedQuery<E> query = entityManager.createQuery(cq);
        return query.getResultList();        
    }
    
    public String save() {
        return save(getModel());
    }
    
    public <E extends GenericEntity> String save(E entity) {
        entityManager.getTransaction().begin();
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
        entityManager.getTransaction().commit();
        return "success";
    }
    
    public void remove(Long idEntity) {
        T entity = find(idEntity);
        if (entity != null) {
            remove(entity);
        }
        /* necessario para que o sistema busque de novo os registros */
        setModelList(null);
    }
    
    private void remove(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch(RollbackException e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir o registro, pois está sendo utilizado por outro registro.", "Não foi possível excluir o registro, pois está sendo utilizado por outro registro."));
        }
    }
    
    public void clearBuffer() {
        entityManager.clear();
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
    
    @PostConstruct
    public void onPostConstruct() {
        entityManager = Persistence.createEntityManagerFactory("casawebPU").createEntityManager();
    }
    
    @PreDestroy
    public void onPreDestroy() {
        entityManager.close();
    }

}
