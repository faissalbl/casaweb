package br.com.fbl.casa.model;

import br.com.fbl.casa.model.Despesa;
import br.com.fbl.casa.model.Recebimento;
import br.com.fbl.casa.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-08T05:52:23")
@StaticMetamodel(MesReferencia.class)
public class MesReferencia_ extends GenericEntity_ {

    public static volatile SingularAttribute<MesReferencia, Long> id;
    public static volatile ListAttribute<MesReferencia, Recebimento> recebimentos;
    public static volatile SingularAttribute<MesReferencia, Integer> mes;
    public static volatile ListAttribute<MesReferencia, Despesa> despesas;
    public static volatile SingularAttribute<MesReferencia, Integer> ano;
    public static volatile ListAttribute<MesReferencia, Usuario> usuarios;
    public static volatile SingularAttribute<MesReferencia, Integer> version;

}