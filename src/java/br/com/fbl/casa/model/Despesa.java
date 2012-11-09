package br.com.fbl.casa.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@SuppressWarnings("serial")
@ManagedBean
@Entity
@Table(name = "despesas")
public class Despesa extends GenericMesReferenciaContent {

    private TipoDespesa tipoDespesa;
    private Double valor;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_despesa_id", nullable = false)
    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }
}
