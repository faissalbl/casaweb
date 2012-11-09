/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.eao.UsuarioEAO;
import br.com.fbl.casa.model.Usuario;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Faissal
 */
@ManagedBean
public class UsuarioManager extends GenericManager<Usuario> {

    private UsuarioEAO usuarioEAO;

    public UsuarioManager() {
        usuarioEAO = new UsuarioEAO();
    }    

    @Override
    protected UsuarioEAO getEAO() {
        return usuarioEAO;
    }    
}
