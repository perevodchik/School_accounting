package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.Discipline;
import data.entity.SchoolClass;
import data.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SchoolClassDao {

    public SchoolClass findById(int id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(SchoolClass.class, id);
    }

    public void save(SchoolClass c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    public void update(SchoolClass c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(c);
        transaction.commit();
        session.close();
    }

    public void delete(SchoolClass c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(c);
        transaction.commit();
        session.close();
    }

    public List<SchoolClass> getAll()
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From classes").list();
    }
}
