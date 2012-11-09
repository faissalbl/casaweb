/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Faissal
 */
@MappedSuperclass
public abstract class GenericMesReferenciaContent extends GenericEntity {
    
    private MesReferencia mesReferencia;
    
    @ManyToOne
    @JoinColumn(name = "mes_referencia_id", nullable = false)
    public MesReferencia getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(MesReferencia mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    
}
