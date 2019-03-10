package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.Position;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PositionDao {

    public Position findById(int id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Position.class, id);
    }

    public void save(Position c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    public void update(Position c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(c);
        transaction.commit();
        session.close();
    }

    public void delete(Position c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(c);
        transaction.commit();
        session.close();
    }

    public List<Position> getAll()
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From position").list();
    }
}
