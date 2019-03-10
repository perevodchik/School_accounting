package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.Discipline;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DisciplineDao {
        public Discipline findById(int id)
        {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Discipline.class, id);
        }

        public void save(Discipline c)
        {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(c);
            transaction.commit();
            session.close();
        }

        public void update(Discipline c)
        {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(c);
            transaction.commit();
            session.close();
        }

        public void delete(Discipline c)
        {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(c);
            transaction.commit();
            session.close();
        }

        public List<Discipline> getAll()
        {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From discipline").list();
        }
}
