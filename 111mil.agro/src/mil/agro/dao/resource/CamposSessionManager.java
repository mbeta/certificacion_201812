package mil.agro.dao.resource;


import java.util.Properties;
import mil.agro.dao.entidades.Campo;
import mil.agro.dao.entidades.EstadoCampo;
import mil.agro.dao.entidades.Lote;
import mil.agro.dao.entidades.TipoSuelo;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class CamposSessionManager {

    private static SessionFactory factory;
    private static String db = "campos";
    public static String servidor = "localhost";
    private static String user= "root";
    private static String pass="5754";
   

    public CamposSessionManager() {
    }

    public static Session getSession() throws HibernateException {
        // Si la fabrica de sesiones ya esta creada, entonces retorna una sesion 
        if (factory != null) {

            return factory.openSession();
        } // Si la fabrica de sesiones no esta creada, entonces crea una y retorna una sesion
        else {
            // Instancia un objeto del tipo Configuration
            Configuration config = new Configuration();

            // Registra los mappers en la configuracion
            registerMappers(config);

            // Establece las propiedades de configuracion
            config.setProperties(getHibernateProperties());

            // Guarda la fabrica de sesiones
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            try {
                factory = config.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Retorna una sesion de trabajo

            return factory.openSession();
        }
    }

    public static void killFactory() {
        factory.close();
        factory = null;
    }

    private static Properties getHibernateProperties() {
        // Instancia un objeto del tipo Properties
        Properties props = new Properties();
        // Establece el driver de conexion dependiente del RDBMS
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        // Establece la url de conexion dependiente del RDBMS  ?zeroDateTimeBehavior=convertToNull
        props.put("hibernate.connection.url", "jdbc:mysql://" + servidor + "/" + db + "?zeroDateTimeBehavior=convertToNull");
        // Establece el usuario        
        props.put("hibernate.connection.username", user);
        // Establece la clave
        props.put("hibernate.connection.password", pass);
        // Establece el dialecto a utilizar
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        // Establece el uso de logging, deber� existir el archivo log4j.properties
        props.put("hibernate.show_sql", "true");
        // Retorna las propiedades
        props.put("org.hibernate.flushMode", "COMMIT");

        return props;
    }

    private static void registerMappers(Configuration config) throws MappingException {
        config.addAnnotatedClass(Campo.class);
        config.addAnnotatedClass(EstadoCampo.class);
        config.addAnnotatedClass(Lote.class);
        config.addAnnotatedClass(TipoSuelo.class);
    }

}
