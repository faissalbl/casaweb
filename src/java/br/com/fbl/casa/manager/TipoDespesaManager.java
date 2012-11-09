/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.eao.TipoDespesaEAO;
import br.com.fbl.casa.model.TipoDespesa;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Faissal
 */
@ManagedBean
public class TipoDespesaManager extends GenericManager<TipoDespesa> {

    private TipoDespesaEAO tipoDespesaEAO;

    public TipoDespesaManager() {
        tipoDespesaEAO = new TipoDespesaEAO();
    }

    @Override
    protected TipoDespesaEAO getEAO() {
        return tipoDespesaEAO;
    }
}