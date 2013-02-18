/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.Despesa;
import br.com.fbl.casa.model.MesReferencia;
import br.com.fbl.casa.model.Recebimento;
import br.com.fbl.casa.model.Usuario;
import br.com.fbl.casa.util.JsfUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

@ManagedBean
public class MesReferenciaMB extends CrudMB<MesReferencia> {
    
    private List<Map<String, String>> summaryData;
    
    @Override
    public void onPostConstruct() {
        super.onPostConstruct();
        Object idMesReferencia = JsfUtil.getSessionProperty(ID_MES_REFERENCIA_KEY);
        if (idMesReferencia != null) {
            setModel(find(idMesReferencia));
        }
    }
    
    @Override
    protected MesReferencia getInstance() {
        return new MesReferencia();
    }

    public String view(Long idMesReferencia) {
        select(idMesReferencia);
        return "view";
    }
    
    public String edit(Long idMesReferencia) {
        select(idMesReferencia);
        return "edit";
    }
    
    private void select(Long idMesReferencia) {
        MesReferencia mesReferencia = find(idMesReferencia);
        setModel(mesReferencia);
        JsfUtil.setSessionProperty(ID_MES_REFERENCIA_KEY, idMesReferencia);
    }
    
    public String createNew() {
        setModel(null);
        JsfUtil.removeSessionProperty(ID_MES_REFERENCIA_KEY);
        return "createNew";
    }
    
    public List<Map<String, String>> getSummaryData() {
        if (summaryData == null) {
            summaryData = new ArrayList<Map<String, String>>();
            List<Usuario> membros = getModel().getUsuarios();
            if (membros != null) {
                double totalDespesas = calcTotalDespesas();
                double rateio = totalDespesas / new Double(membros.size());            
                for (Usuario u: membros) {
                    Map<String, String> data = new LinkedHashMap<String, String>();
                    double totalRecebimentos = calcTotalRecebimentos();
                    data.put("usuario", u.getNome());
                    data.put("totalRecebimentos", String.valueOf(totalRecebimentos));
                    data.put("saldoDevedor", String.valueOf(rateio - totalRecebimentos));
                    summaryData.add(data);
                }
            }
        }
        return summaryData;
    }

    public double calcTotalDespesas() {
        double total = 0;
        Collection<Despesa> despesas = findAll(Despesa.class);
        if (despesas != null) {
            for (Despesa d: despesas) {
                total += d.getValor();
            }
        }
        return total;
    }

    public double calcTotalRecebimentos() {
        double total = 0;
        Collection<Recebimento> recebimentos = findAll(Recebimento.class);
        if (recebimentos != null) {
            for (Recebimento r: recebimentos) {
                total += r.getValor();
            }
        }
        return total;
    }
    
    public void resetSummary(ActionEvent event) {
        summaryData = null;
    }

}
