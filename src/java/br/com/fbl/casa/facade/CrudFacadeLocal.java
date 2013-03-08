/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.facade;

import br.com.fbl.casa.model.GenericEntity;
import br.com.fbl.casa.persistence.facade.exception.PersistenceRollbackException;
import java.util.Collection;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Faissal
 */
@Local
public interface CrudFacadeLocal {
    
    public <T extends GenericEntity> T find(Class<T> clazz, Object id);
    
    public <T extends GenericEntity> Collection<T> findAll(Class<T> clazz, Map<String, Object> queryParams);
    
    public <T extends GenericEntity> void save(T entity);
    
    public <T extends GenericEntity> void remove(Class<T> clazz, Long idEntity) throws PersistenceRollbackException;
}
