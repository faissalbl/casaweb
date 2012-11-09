/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.eao.RecebimentoEAO;
import br.com.fbl.casa.model.Recebimento;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Faissal
 */
@ManagedBean
public class RecebimentoManager extends GenericManager<Recebimento> {
    
    private RecebimentoEAO recebimentoEAO;
    
    public RecebimentoManager() {
        recebimentoEAO = new RecebimentoEAO();
    }

    @Override
    protected RecebimentoEAO getEAO() {
        return recebimentoEAO;
    }    
}
