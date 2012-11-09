/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.eao;

import br.com.fbl.casa.model.GenericEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Faissal
 */
public abstract class GenericEAO<T extends GenericEntity> {
    
    private static EntityManagerFactory entityManagerFactory;    
    protected static EntityManager entityManager;
   
    public GenericEAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("casawebPU");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    public abstract List<T> getResultList();
    
    public T find(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }
    
    public void persist(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    
    public T merge(T entity) {
        T result;
        entityManager.getTransaction().begin();        
        result = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return result;
    }
        
    public void remove(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(entity));
        entityManager.getTransaction().commit();
        getResultList().remove(entity);
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
