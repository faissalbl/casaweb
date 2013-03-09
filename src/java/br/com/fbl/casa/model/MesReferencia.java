package br.com.fbl.casa.model;

import br.com.fbl.crud.model.GenericEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "MesReferencia")
public class MesReferencia extends GenericEntity {

    private Long id;
    private Integer version;
    private Integer mes;
    private Integer ano;
    private List<Despesa> despesas;
    private List<Recebimento> recebimentos;
    private List<Usuario> usuarios;

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

    @OneToMany(mappedBy = "mesReferencia", cascade = CascadeType.ALL)
    public List<Despesa> getDespesas() {
        if (despesas == null) {
            despesas = new ArrayList<Despesa>();
        }        
        return despesas;
    }
    
    public void setRecebimentos(List<Recebimento> recebimentos) {
        this.recebimentos = recebimentos;
    }
    
    @OneToMany(mappedBy = "mesReferencia", cascade = CascadeType.ALL)
    public List<Recebimento> getRecebimentos() {
        if (recebimentos == null) {
            recebimentos = new ArrayList<Recebimento>();
        }
        
        return recebimentos;
    } 

    @ManyToMany
    @JoinTable(name="MesReferenciaUsuario", 
            joinColumns={@JoinColumn(name="idMesReferencia")},
            inverseJoinColumns={@JoinColumn(name="idUsuario")})
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
