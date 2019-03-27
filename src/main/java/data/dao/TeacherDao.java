package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TeacherDao {

    public Teacher findById(int id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Teacher.class, id);
    }

    public void save(Teacher c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    public void update(Teacher c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(c);
        transaction.commit();
        session.close();
    }

    public void delete(Teacher c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(c);
        transaction.commit();
        session.close();
    }

    public List getAll()
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From teachers").list();
    }
}
