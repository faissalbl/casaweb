package br.com.fbl.crud.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericEntity implements Serializable {
    
    public abstract Object getId();

}
