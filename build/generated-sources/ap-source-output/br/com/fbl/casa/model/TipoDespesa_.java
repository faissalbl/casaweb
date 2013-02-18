package br.com.fbl.casa.model;

import br.com.fbl.casa.model.Despesa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-02-17T21:14:05")
@StaticMetamodel(TipoDespesa.class)
public class TipoDespesa_ extends TipoAbstrato_ {

    public static volatile SingularAttribute<TipoDespesa, Long> id;
    public static volatile ListAttribute<TipoDespesa, Despesa> despesas;
    public static volatile SingularAttribute<TipoDespesa, Integer> version;

}