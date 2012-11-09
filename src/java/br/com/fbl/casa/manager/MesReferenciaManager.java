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
    private String selectedMesReferencia;
    
    private final int PATTERN_DYY_LENGTH = 3;
    private final int PATTERN_DDYY_LENGTH = 4;
    private final int PATTERN_DYYYY_LENGTH = 5;
    private final int PATTERN_DDYYYY_LENGTH = 6;
    
    public MesReferenciaManager() {
        mesReferenciaEAO = new MesReferenciaEAO();
    }
    
    @Override
    protected MesReferenciaEAO getEAO() {
        return mesReferenciaEAO;
    }
    
    @Override
    public String createNew() {
        JsfUtil.setSessionProperty("mesReferencia", new MesReferencia());
        return super.createNew();
    }
    
    public String select() {
        if (!validateMesReferencia(selectedMesReferencia)) {
            FacesContext.getCurrentInstance().addMessage("mesReferencia", new FacesMessage(FacesMessage.SEVERITY_ERROR, "mes referencia inválido", "mes referencia inválido"));
            return null;
        }
        Integer mesAno[] = extractMesAno(selectedMesReferencia);
        MesReferencia mesReferencia = null;
        try {
            mesReferencia = mesReferenciaEAO.findByMesAno(mesAno);
        } catch (NonUniqueResultException ex) {
            FacesContext.getCurrentInstance().addMessage("mesReferencia", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.getMessage()));
            return null;
        }
        String outcome;
        if (mesReferencia == null) {
            mesReferencia = new MesReferencia();
            mesReferencia.setMes(mesAno[0]);
            mesReferencia.setAno(mesAno[1]);
            outcome = "new";
        } else {
            outcome = "view";
        }
        JsfUtil.setSessionProperty("mesReferencia", mesReferencia);
        return outcome;
    }
    
    private boolean validateMesReferencia(String mesReferencia) {
        if (mesReferencia != null && (
                (mesReferencia.length() == PATTERN_DYY_LENGTH || mesReferencia.length() == PATTERN_DYYYY_LENGTH /* 112 ou 12012 rep 1/2012 */ && Integer.parseInt(mesReferencia.substring(0, 1)) > 0 && Integer.parseInt(mesReferencia.substring(0, 1)) <= 12) 
                    || (mesReferencia.length() == PATTERN_DDYY_LENGTH || mesReferencia.length() == PATTERN_DDYYYY_LENGTH /* 0112 ou 012012 rep 01/2012 */ && Integer.parseInt(mesReferencia.substring(0, 2)) > 0 && Integer.parseInt(mesReferencia.substring(0, 2)) <= 12)))
            return true;
        return false;
    }
    
    private Integer[] extractMesAno(String mesReferencia) {
        Integer[] mesAno = new Integer[2];
        if (mesReferencia.length() == PATTERN_DYY_LENGTH || mesReferencia.length() == PATTERN_DYYYY_LENGTH) {
            mesAno[0] = Integer.parseInt(mesReferencia.substring(0, 1));
            mesAno[1] = Integer.parseInt(mesReferencia.substring(1));
        } else if (mesReferencia.length() == PATTERN_DDYY_LENGTH || mesReferencia.length() == PATTERN_DDYYYY_LENGTH) {
            mesAno[0] = Integer.parseInt(mesReferencia.substring(0, 2));
            mesAno[1] = Integer.parseInt(mesReferencia.substring(2));
        }
        if (mesAno[1].toString().length() == 2)
            mesAno[1] = Integer.parseInt("20" + mesAno[1].toString());
        return mesAno;
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

    /**
     * @return the selectedMesReferencia
     */
    public String getSelectedMesReferencia() {
        return selectedMesReferencia;
    }

    /**
     * ignores all non digit characters
     * @param selectedMesReferencia the selectedMesReferencia to set
     */
    public void setSelectedMesReferencia(String selectedMesReferencia) {
        selectedMesReferencia = selectedMesReferencia.replaceAll("[^0-9]", "");
        this.selectedMesReferencia = selectedMesReferencia;
    }

}
