/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.TipoDespesa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Faissal
 */
@ManagedBean
@ViewScoped
public class TipoDespesaMB extends CrudMB<TipoDespesa> {

    @Override
    protected TipoDespesa getInstance() {
        return new TipoDespesa();
    }

}