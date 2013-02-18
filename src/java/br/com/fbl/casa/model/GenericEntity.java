package br.com.fbl.casa.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericEntity implements Serializable {
    
    public abstract Object getId();

}
