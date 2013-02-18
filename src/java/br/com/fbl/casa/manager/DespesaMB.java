/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.Despesa;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Faissal
 */
@ManagedBean
public class DespesaMB extends MesReferenciaContentMB<Despesa> {

    @Override
    public Despesa getInstance() {
        Despesa despesa = new Despesa();
        return despesa;
    }

}
