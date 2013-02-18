package br.com.fbl.casa.model;

import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TipoAbstrato extends GenericEntity {

    private String nome;

    public String getNome() { 
        return nome; 
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
