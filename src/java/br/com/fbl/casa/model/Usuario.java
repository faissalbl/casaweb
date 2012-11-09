package br.com.fbl.casa.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@ManagedBean
@Entity
@Table(name = "usuarios")
@NamedQueries(value = {
    @NamedQuery(name="Usuario.findUsuarios", query="select u from Usuario u where (:pNome is null or u.nome = :pNome) order by u.nome")
})
public class Usuario extends GenericEntity {

    // seam-gen attributes (you should probably edit these)
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getPassword() {
        return password;
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
