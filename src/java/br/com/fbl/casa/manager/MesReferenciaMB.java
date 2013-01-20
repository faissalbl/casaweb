/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fbl.casa.manager;

import br.com.fbl.casa.model.MesReferencia;
import br.com.fbl.casa.util.JsfUtil;
import java.util.Collection;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class MesReferenciaMB extends CrudMB<MesReferencia> {
    
    private Collection<MesReferencia> mesesReferencia;

    @Override
    public MesReferencia getInstance() {
        return new MesReferencia();
    }

    public String select(Long idMesReferencia) {
        MesReferencia mesReferencia = find(idMesReferencia);
        JsfUtil.setSessionProperty("mesReferencia", mesReferencia);
        return "view";
    }
    
    public String createNew() {
        JsfUtil.setSessionProperty("mesReferencia", new MesReferencia());
        return "new";
    }
    
    @Override
    public void remove(Long idEntity) {
        super.remove(idEntity);
        setMesesReferencia(null);
    }

    public Collection<MesReferencia> getMesesReferencia() {
        if (mesesReferencia == null) {
            mesesReferencia = findAll();
        }
        return mesesReferencia;
    }

    public void setMesesReferencia(Collection<MesReferencia> mesesReferencia) {
        this.mesesReferencia = mesesReferencia;
    }   
    
}
