package br.com.fbl.casa.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@SuppressWarnings("serial")
@ManagedBean
@Entity
@Table(name = "recebimentos")
public class Recebimento extends GenericMesReferenciaContent {

    private Usuario usuario;
    private TipoDespesa tipoDespesa;
    private Double valor;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_despesa_id")
    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }
}
