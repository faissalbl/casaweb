package br.com.fbl.casa.model;

import br.com.fbl.casa.model.TipoDespesa;
import br.com.fbl.casa.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-02-17T21:14:05")
@StaticMetamodel(Recebimento.class)
public class Recebimento_ extends GenericMesReferenciaContent_ {

    public static volatile SingularAttribute<Recebimento, Long> id;
    public static volatile SingularAttribute<Recebimento, TipoDespesa> tipoDespesa;
    public static volatile SingularAttribute<Recebimento, Double> valor;
    public static volatile SingularAttribute<Recebimento, Usuario> usuario;
    public static volatile SingularAttribute<Recebimento, Integer> version;

}