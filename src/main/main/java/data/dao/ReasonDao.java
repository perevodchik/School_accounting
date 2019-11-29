package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.Reason;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReasonDao {

    public Reason findById(int id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Reason.class, id);
    }

    public void save(Reason c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    public void update(Reason c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(c);
        transaction.commit();
        session.close();
    }

    public void delete(Reason c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(c);
        transaction.commit();
        session.close();
    }

    public List getAll()
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From reason").list();
    }
}
