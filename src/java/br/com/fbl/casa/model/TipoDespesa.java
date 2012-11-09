package br.com.fbl.casa.model;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@ManagedBean
@Entity
@Table(name="tipo_despesas")
@NamedQueries(value = {
    @NamedQuery(name="TipoDespesa.findTiposDespesa", query="select td from TipoDespesa td where (:pNome is null or td.nome = :pNome)")
})
public class TipoDespesa extends TipoAbstrato {

    private List<Despesa> despesas;

    @OneToMany(mappedBy = "tipoDespesa")
    public List<Despesa> getDespesas() {
        
        if (despesas == null)
            despesas = new ArrayList<Despesa>();
        
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null
                && obj.equals(getNome());
    }

 }
