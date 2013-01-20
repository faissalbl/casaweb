/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.eao.DespesaEAO;
import br.com.fbl.casa.eao.MesReferenciaEAO;
import br.com.fbl.casa.eao.RecebimentoEAO;
import br.com.fbl.casa.eao.UsuarioEAO;
import br.com.fbl.casa.model.Despesa;
import br.com.fbl.casa.model.MesReferencia;
import br.com.fbl.casa.model.Recebimento;
import br.com.fbl.casa.model.Usuario;
import br.com.fbl.casa.util.JsfUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Faissal
 */
@ManagedBean
public class MesReferenciaManager extends GenericManager<MesReferencia> {
    
    private MesReferenciaEAO mesReferenciaEAO;
    private List<Map<String, String>> summaryData;
    
    public MesReferenciaManager() {
        mesReferenciaEAO = new MesReferenciaEAO();
    }
    
    @Override
    protected MesReferenciaEAO getEAO() {
        return mesReferenciaEAO;
    }
    
    public double calcTotalDespesas(List<Despesa> despesas) {
        double total = 0;
        for (Despesa d: despesas) {
            total += d.getValor();
        }
        return total;
    }

    public double calcTotalRecebimentos(List<Recebimento> recebimentos) {
        double total = 0;
        for (Recebimento r: recebimentos) {
            total += r.getValor();
        }
        return total;
    }
    
    public List<Map<String, String>> getSummaryData(MesReferencia mesReferencia) {
        if (summaryData == null) {
            summaryData = new ArrayList<Map<String, String>>();
            List<Usuario> membros = new UsuarioEAO().getResultList();
            double totalDespesas = calcTotalDespesas(new DespesaEAO().getResultList());
            double rateio = totalDespesas / new Double(membros.size());
            
            for (Usuario u: membros) {
                Map<String, String> data = new LinkedHashMap<String, String>();
                double totalRecebimentos = calcTotalRecebimentos(new RecebimentoEAO().findRecebimentos(u.getId()));
                data.put("usuario", u.getNome());
                data.put("totalRecebimentos", String.valueOf(totalRecebimentos));
                data.put("saldoDevedor", String.valueOf(rateio - totalRecebimentos));
                summaryData.add(data);
            }
        }
        return summaryData;
    }

}
