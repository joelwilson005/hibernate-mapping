package info.joelwilson.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    static {

        try {
            standardServiceRegistry = new StandardServiceRegistryBuilder().configure()
                    .build();

            Metadata metadata = new MetadataSources(standardServiceRegistry)
                    .getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch(HibernateException exception) {

            exception.printStackTrace();
            System.out.println("Problem creating session factory.");
        }
    }



    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }


    public static void destroyRegistry() {

        StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
    }
}
