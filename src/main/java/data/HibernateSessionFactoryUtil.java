package data;

import data.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                initAnnotatedClasses(configuration);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
        return sessionFactory;
    }

    private static void initAnnotatedClasses(Configuration configuration)
    {
        configuration.addAnnotatedClass(Discipline.class);
        configuration.addAnnotatedClass(LessonSchedule.class);
        configuration.addAnnotatedClass(Point.class);
        configuration.addAnnotatedClass(Position.class);
        configuration.addAnnotatedClass(Reason.class);
        configuration.addAnnotatedClass(SchoolClass.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Teacher.class);
    }
}
