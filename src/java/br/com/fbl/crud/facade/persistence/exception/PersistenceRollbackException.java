/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.crud.facade.persistence.exception;

import javax.ejb.ApplicationException;

/**
 * Checked exception. Rolls back transaction if thrown.
 * @author Faissal
 */
@ApplicationException(rollback=true)
public class PersistenceRollbackException extends Exception {
    
    public PersistenceRollbackException() {
        super();
    }
    
    public PersistenceRollbackException(String msg) {
        super(msg);
    }
    
}
