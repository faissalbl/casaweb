/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.GenericMesReferenciaContent;
import br.com.fbl.casa.model.MesReferencia;
import br.com.fbl.casa.util.JsfUtil;
import br.com.fbl.crud.mb.CrudMB;

/**
 *
 * @author Faissal
 */
public abstract class MesReferenciaContentMB<T extends GenericMesReferenciaContent> extends CrudMB<T> {
    
    @Override
    public T getModel() {
        T model = super.getModel();
        if (model.getMesReferencia() == null) {
            model.setMesReferencia(find(MesReferencia.class, JsfUtil.getSessionProperty(ID_MES_REFERENCIA_KEY)));
            getQueryParams().put("mesReferencia", model.getMesReferencia());
        }
        return model;
    }

}
