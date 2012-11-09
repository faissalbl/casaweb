/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.eao;

import br.com.fbl.casa.model.Recebimento;
import java.util.List;

/**
 *
 * @author Faissal
 */
public class RecebimentoEAO extends GenericMesReferenciaContentEAO<Recebimento> {
    
    private List<Recebimento> recebimentos;

    @Override
    public List<Recebimento> getResultList() {
        if (recebimentos == null)
            recebimentos = findRecebimentos();
        return recebimentos;
    }
    
    private List<Recebimento> findRecebimentos() {
        return getEntityManager().createQuery(
                "select r from Recebimento r where r.mesReferencia.id = :pMesReferenciaId")
                .setParameter("pMesReferenciaId", mesReferencia.getId())
                .getResultList();
    }
    
    public List<Recebimento> findRecebimentos(Long usuarioId) {
        return getEntityManager().createQuery(
                "select r from Recebimento r where r.mesReferencia.id = :pMesReferenciaId and r.usuario.id = :pUsuarioId")
                .setParameter("pMesReferenciaId", mesReferencia.getId())
                .setParameter("pUsuarioId", usuarioId)
                .getResultList();
    }
    
}
