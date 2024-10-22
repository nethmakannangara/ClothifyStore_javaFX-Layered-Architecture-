package utill;

import entity.AdminEntity;
import entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory ;

    public static SessionFactory createSession(HibernateUtilType type){
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metaData = null;
        switch (type){
            case ADMIN -> {
                metaData = new MetadataSources(build)
                        .addAnnotatedClass(AdminEntity.class)
                        .getMetadataBuilder()
                        .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                        .build();
            }
            case EMPLOYEE -> {
                metaData = new MetadataSources(build)
                        .addAnnotatedClass(EmployeeEntity.class)
                        .getMetadataBuilder()
                        .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                        .build();
            }
        }

        return metaData.getSessionFactoryBuilder().build();

    }

    public static Session getSession(HibernateUtilType type){
        return createSession(type).openSession();
    }
}