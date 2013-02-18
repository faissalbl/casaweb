/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class UsuarioMB extends CrudMB<Usuario> {
    
    @Override
    public void onPostConstruct() {
        super.onPostConstruct();
        String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario");
        if (idUsuario != null) {
            setModel(find(Long.valueOf(idUsuario)));
        }
    }

    @Override
    protected Usuario getInstance() {
        return new Usuario();
    }

}
