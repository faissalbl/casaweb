/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.eao;

import br.com.fbl.casa.model.MesReferencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Faissal
 */
public class MesReferenciaEAO extends GenericEAO<MesReferencia> {

    private MesReferencia mesReferenciaExample;
    private List<MesReferencia> resultList;

    public MesReferenciaEAO() {
        mesReferenciaExample = new MesReferencia();
        resultList = new ArrayList<MesReferencia>();
    }

    public void refreshResultList() {
        resultList = getEntityManager().createNamedQuery(
                "MesReferencia.findMesesReferencia")
                .setParameter("pMes", mesReferenciaExample.getMes())
                .setParameter("pAno", mesReferenciaExample.getAno())
                .getResultList();
    }
    
    public MesReferencia getMesReferenciaExample() {
        return mesReferenciaExample;
    }
    
    @Override
    public List<MesReferencia> getResultList() {
        if (resultList.isEmpty()) refreshResultList();
        return resultList;
    }

    public MesReferencia findByMesAno(Integer[] mesAno) {
        MesReferencia mesReferencia = null;
        try {
            mesReferencia = (MesReferencia)
                    entityManager.createQuery("select mr from MesReferencia mr where mr.mes = :mes and mr.ano = :ano")
                        .setParameter("mes", mesAno[0])
                        .setParameter("ano", mesAno[1])
                        .getSingleResult();
        } catch (NoResultException nre) {
        } catch (NonUniqueResultException nure) {
            throw new NonUniqueResultException("Há mais de um mês de referência no mesmo ano");
        }
        
        return mesReferencia;        
    }
}
