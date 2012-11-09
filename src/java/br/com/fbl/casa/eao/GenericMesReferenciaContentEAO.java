/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.eao;

import br.com.fbl.casa.model.GenericEntity;
import br.com.fbl.casa.model.GenericMesReferenciaContent;
import br.com.fbl.casa.model.MesReferencia;
import javax.faces.context.FacesContext;

/**
 *
 * @author Faissal
 */
public abstract class GenericMesReferenciaContentEAO<T extends GenericEntity> extends GenericEAO<T> {
    
    protected MesReferencia mesReferencia;
    
    public GenericMesReferenciaContentEAO() {
        mesReferencia = (MesReferencia) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("mesReferencia");
    }
    
    @Override
    public void persist(T entity) {
        ((GenericMesReferenciaContent) entity).setMesReferencia(mesReferencia);
        super.persist(entity);
    }
}
