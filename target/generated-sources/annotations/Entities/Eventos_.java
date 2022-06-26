package Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-26T03:47:49")
@StaticMetamodel(Eventos.class)
public class Eventos_ { 

    public static volatile SingularAttribute<Eventos, Date> fecha;
    public static volatile SingularAttribute<Eventos, Integer> identifier;
    public static volatile SingularAttribute<Eventos, String> ciudad;
    public static volatile SingularAttribute<Eventos, Integer> entradasDisponibles;
    public static volatile SingularAttribute<Eventos, String> nombre;
    public static volatile SingularAttribute<Eventos, String> estadio;
    public static volatile SingularAttribute<Eventos, Integer> entradasTotales;
    public static volatile SingularAttribute<Eventos, Integer> organizador;

}