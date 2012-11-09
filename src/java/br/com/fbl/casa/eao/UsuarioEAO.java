/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.eao;

import br.com.fbl.casa.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Faissal
 */
public class UsuarioEAO extends GenericEAO<Usuario> {

    private Usuario usuarioExample;
    private List<Usuario> resultList;

    public UsuarioEAO() {
        usuarioExample = new Usuario();
        resultList = new ArrayList<Usuario>();
    }

    private void refreshResultList() {
        resultList = getEntityManager().createNamedQuery(
                "Usuario.findUsuarios")
                .setParameter("pNome", usuarioExample.getNome())
                .getResultList();
    }
    
    public Usuario getUsuarioExample() {
        return usuarioExample;
    }
    
    @Override
    public List<Usuario> getResultList() {
        if (resultList.isEmpty()) refreshResultList();
        return resultList;
    }
}
