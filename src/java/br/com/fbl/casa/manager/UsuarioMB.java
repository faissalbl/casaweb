/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.Usuario;
import br.com.fbl.crud.mb.CrudMB;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UsuarioMB extends CrudMB<Usuario> {
    
    @PostConstruct
    public void onPostConstruct() {
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
