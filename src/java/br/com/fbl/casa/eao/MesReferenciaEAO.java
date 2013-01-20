/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.eao;

import br.com.fbl.casa.model.MesReferencia;
import java.util.ArrayList;
import java.util.List;

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

}
