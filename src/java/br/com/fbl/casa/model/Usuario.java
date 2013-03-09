package br.com.fbl.casa.model;

import br.com.fbl.crud.model.GenericEntity;
import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario extends GenericEntity {

    // seam-gen attributes (you should probably edit these)
    private Long id;
    private Integer version;
    private String nome;
    private String password;
    private Boolean administrator;

    @Column(nullable=false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdministrator(Boolean administrator) {
        if (administrator == null) {
            administrator = Boolean.FALSE;
        }
        this.administrator = administrator;
    }

    public Boolean getAdministrator() {
        return administrator;
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
