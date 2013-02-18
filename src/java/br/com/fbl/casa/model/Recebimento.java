package br.com.fbl.casa.model;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Recebimento")
public class Recebimento extends GenericMesReferenciaContent {

    private Long id;
    private Integer version;
    private Usuario usuario;
    private TipoDespesa tipoDespesa;
    private Double valor;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
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
    @JoinColumn(name = "idTipoDespesa")
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
