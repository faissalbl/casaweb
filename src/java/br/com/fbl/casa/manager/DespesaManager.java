/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.eao.DespesaEAO;
import br.com.fbl.casa.model.Despesa;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Faissal
 */
@ManagedBean
public class DespesaManager extends GenericManager<Despesa> {
    
    private DespesaEAO despesaEAO;
    
    public DespesaManager() {
        despesaEAO = new DespesaEAO();
    }

    @Override
    protected DespesaEAO getEAO() {
        return despesaEAO;
    }

}
