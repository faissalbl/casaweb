/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.Recebimento;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Faissal
 */
@ManagedBean
@ViewScoped
public class RecebimentoMB extends MesReferenciaContentMB<Recebimento> {

    @Override
    public Recebimento getInstance() {
        Recebimento recebimento = new Recebimento();
        return recebimento;
    }
    
}
