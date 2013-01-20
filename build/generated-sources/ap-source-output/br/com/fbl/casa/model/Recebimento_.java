package br.com.fbl.casa.model;

import br.com.fbl.casa.model.TipoDespesa;
import br.com.fbl.casa.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-01-20T17:34:21")
@StaticMetamodel(Recebimento.class)
public class Recebimento_ extends GenericMesReferenciaContent_ {

    public static volatile SingularAttribute<Recebimento, TipoDespesa> tipoDespesa;
    public static volatile SingularAttribute<Recebimento, Double> valor;
    public static volatile SingularAttribute<Recebimento, Usuario> usuario;

}