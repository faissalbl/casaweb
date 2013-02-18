package br.com.fbl.casa.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="TipoDespesa")
public class TipoDespesa extends TipoAbstrato {

    private Long id;
    private Integer version;
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

    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
