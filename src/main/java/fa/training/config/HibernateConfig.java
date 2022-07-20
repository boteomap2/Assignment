package fa.training.config;


import java.util.Properties;
import java.util.Set;
import javax.persistence.Entity;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

public class HibernateConfig {
    private enum DB {
        DB_DRIVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),
        SERVER_ADDRESS("localhost"),
        DB_NAME("backend_assignment"),
        SERVER_PORT("1434"),
        DIALECT("org.hibernate.dialect.SQLServer2012Dialect"),
        BASE_ENTITES_PACKAGE("fa.training.entity");

        private final String value;

        DB(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private HibernateConfig() {
    }

    public static final String URL = "jdbc:sqlserver://" + DB.SERVER_ADDRESS.getValue() + ":"
            + DB.SERVER_PORT.getValue() + ";database=" + DB.DB_NAME.getValue()
            + ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

    private static final SessionFactory sessionFactory = createSessionFactory();
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static final Validator validator = createValidator();
    public static Validator getValidator() {
        return validator;
    }
    private static SessionFactory createSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration hibernateConfiguration = new Configuration();

            Properties hibernateProperties = new Properties();
            // The JDBC driver class.
            hibernateProperties.put(Environment.DRIVER, DB.DB_DRIVER.getValue());
            // The JDBC URL to the database instance.
            hibernateProperties.put(Environment.URL, URL);
            // This property makes Hibernate generate the appropriate SQL for the chosen database.
            hibernateProperties.put(Environment.DIALECT, DB.DIALECT.getValue());
            // Enable logging of generated SQL to the console
            hibernateProperties.put(Environment.SHOW_SQL, "true");
            // Enable formatting of SQL logged to the console
            hibernateProperties.put(Environment.FORMAT_SQL, "true");
            // Context scoping impl for SessionFactory.getCurrentSession() processing.
            hibernateProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            // Setting to perform SchemaManagementTool actions automatically as part of the SessionFactory lifecycle.
            hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");
            // Minimum size of C3P0 connection pool
            hibernateProperties.put(Environment.C3P0_MIN_SIZE, "5");
            // Maximum size of C3P0 connection pool
            hibernateProperties.put(Environment.C3P0_MAX_SIZE, "20");
            // Number of connections acquired when pool is exhausted
            hibernateProperties.put(Environment.C3P0_ACQUIRE_INCREMENT, "5");
            // Maximum idle time for C3P0 connection pool
            hibernateProperties.put(Environment.C3P0_TIMEOUT, "1800");


            hibernateConfiguration.setProperties(hibernateProperties);

            Set<Class<?>> classes = new Reflections(DB.BASE_ENTITES_PACKAGE.getValue())
                .getTypesAnnotatedWith(Entity.class);

            if (classes.isEmpty()) {
                System.out.println("Fail to scan Entities.");
                return null;
            }

            for (Class<?> class1 : classes) {
                hibernateConfiguration.addAnnotatedClass(class1);
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(hibernateConfiguration.getProperties()).build();

            sessionFactory = hibernateConfiguration.buildSessionFactory(
                serviceRegistry);

            if (sessionFactory != null) {
                System.out.println("Create Session factory successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    private static Validator createValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }
}
