/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.facade;

import br.com.fbl.casa.model.GenericEntity;
import br.com.fbl.casa.persistence.facade.exception.PersistenceRollbackException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Faissal
 */
@Stateless
public class CrudFacade implements CrudFacadeLocal {

    @PersistenceContext(unitName="casawebPU")
    private EntityManager entityManager;

    @Override
    public <T extends GenericEntity> T find(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }
    
    @Override
    public <T extends GenericEntity> Collection<T> findAll(Class<T> clazz, Map<String, Object> queryParams) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        if (!queryParams.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (String paramKey: queryParams.keySet()) {
                predicates.add(cb.equal(root.get(paramKey), queryParams.get(paramKey)));
            }
            cq.where((Predicate[])predicates.toArray(new Predicate[predicates.size()]));
        }
        TypedQuery<T> query = entityManager.createQuery(cq);
        return query.getResultList();        
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends GenericEntity> void save(T entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T extends GenericEntity> void remove(Class<T> clazz, Long idEntity) throws PersistenceRollbackException {
        T entity = find(clazz, idEntity);
        try {
            entityManager.remove(entity);
        } catch(RollbackException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Não foi possível excluir o registro, pois está sendo utilizado por outro registro. \n" + e.getMessage());
            throw new PersistenceRollbackException("Não foi possível excluir o registro, pois está sendo utilizado por outro registro.");
        }
    }
    

}
