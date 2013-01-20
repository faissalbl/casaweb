package br.com.fbl.casa.model;

import br.com.fbl.casa.model.Despesa;
import br.com.fbl.casa.model.Recebimento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-01-20T17:34:21")
@StaticMetamodel(MesReferencia.class)
public class MesReferencia_ extends GenericEntity_ {

    public static volatile ListAttribute<MesReferencia, Recebimento> recebimentos;
    public static volatile SingularAttribute<MesReferencia, Integer> mes;
    public static volatile ListAttribute<MesReferencia, Despesa> despesas;
    public static volatile SingularAttribute<MesReferencia, Integer> ano;

}