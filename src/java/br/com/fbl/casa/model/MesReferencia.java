package br.com.fbl.casa.model;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@ManagedBean
@SessionScoped
@Entity
@Table(name = "mes_referencias")
@NamedQueries(value={
    @NamedQuery(name="MesReferencia.findMesesReferencia", query="select mr from MesReferencia mr where (:pMes is null or mr.mes = :pMes) and (:pAno is null or mr.ano = :pAno)")
})
public class MesReferencia extends GenericEntity {

    private Integer mes;
    private Integer ano;
    private List<Despesa> despesas;
    private List<Recebimento> recebimentos;

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getMes() {
        return mes;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getAno() {
        return ano;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    @OneToMany(mappedBy = "mesReferencia", cascade = CascadeType.REMOVE)
    public List<Despesa> getDespesas() {
        if (despesas == null)
            despesas = new ArrayList<Despesa>();
        
        return despesas;
    }
    
    public void setRecebimentos(List<Recebimento> recebimentos) {
        this.recebimentos = recebimentos;
    }
    
    @OneToMany(mappedBy = "mesReferencia", cascade = CascadeType.REMOVE)
    public List<Recebimento> getRecebimentos() {
        if (recebimentos == null)
            recebimentos = new ArrayList<Recebimento>();
        
        return recebimentos;
    } 
    
    @Transient
    public String getDscMesReferencia() {

        String dscMesReferencia = null;

        if (mes != null && ano != null) {
            dscMesReferencia = mes + " / " + ano;
        }

        return dscMesReferencia;
    }

    @Override
    public String toString() {
        String result = "";
        if (mes != null && ano != null) {
            result = mes + "/" + ano;
        }
        return result;
    }
}
