package br.com.fbl.casa.model;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Despesa")
public class Despesa extends GenericMesReferenciaContent {

    private Long id;
    private Integer version;
    private TipoDespesa tipoDespesa;
    private Double valor;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @ManyToOne
    @JoinColumn(name = "idTipoDespesa", nullable = false)
    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
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
}
