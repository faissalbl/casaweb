/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.eao;

import br.com.fbl.casa.model.Despesa;
import java.util.List;

/**
 *
 * @author Faissal
 */
public class DespesaEAO extends GenericMesReferenciaContentEAO<Despesa> {
    
    private List<Despesa> despesas;

    @Override
    public List<Despesa> getResultList() {
        if (despesas == null)
            despesas = findDespesas();
        return despesas;
    }
    
    private List<Despesa> findDespesas() {
        return getEntityManager().createQuery(
                "select d from Despesa d where d.mesReferencia.id = :pMesReferenciaId")
                .setParameter("pMesReferenciaId", mesReferencia.getId())
                .getResultList();
    }
}
