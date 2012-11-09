/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.eao;

import br.com.fbl.casa.model.TipoDespesa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Faissal
 */
public class TipoDespesaEAO extends GenericEAO<TipoDespesa> {

    private TipoDespesa tipoDespesaExample;
    private List<TipoDespesa> resultList;

    public TipoDespesaEAO() {
        tipoDespesaExample = new TipoDespesa();
        resultList = new ArrayList<TipoDespesa>();
    }

    private void refreshResultList() {
        resultList = getEntityManager().createNamedQuery(
                "TipoDespesa.findTiposDespesa")
                .setParameter("pNome", tipoDespesaExample.getNome())
                .getResultList();
    }
    
    public TipoDespesa getTipoDespesaExample() {
        return tipoDespesaExample;
    }
    
    @Override
    public List<TipoDespesa> getResultList() {
        if (resultList.isEmpty()) refreshResultList();
        return resultList;
    }
}
