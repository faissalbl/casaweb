/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.GenericEntity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Faissal
 */
//TODO passar toda lógica de negócio e dados para uma outra camada.
public abstract class CrudMB<T extends GenericEntity> {
    
    protected EntityManager entityManager;
    
    private T model;
    private Map<String, Object> queryParams;
    
    public T find(Long id) {
        return entityManager.find((Class<T>)model.getClass(), id);
    }
    
    /**
     * Finds all entities constrained by the <code>queryParams</code> content.
     */
    public Collection<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery((Class<T>)getModel().getClass());
        Root<T> root = cq.from((Class<T>)getModel().getClass());
        cq.select(root);
        if (!getQueryParams().isEmpty()) {
            for (String paramKey: queryParams.keySet()) {
                cq.where(cb.equal(root.get(paramKey), queryParams.get(paramKey)));
            }
        }
        TypedQuery<T> query = entityManager.createQuery(cq);
        return query.getResultList();        
    }
    
    public void remove(Long idEntity) {
        T entity = find(idEntity);
        if (entity != null)
            remove(entity);
    }
    
    public void remove(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
    
    public T getModel() {
        if (model == null) {
            model = getInstance();
        }
        return model;
    }
    
    public abstract T getInstance();
    
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
