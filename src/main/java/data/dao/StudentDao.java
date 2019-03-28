package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.SchoolClass;
import data.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StudentDao {

    public Student findById(int id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Student.class, id);
    }

    public void save(Student c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    public void update(Student c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(c);
        transaction.commit();
        session.close();
    }

    public void delete(Student c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(c);
        transaction.commit();
        session.close();
    }

    public List<Student> getFromSchoolClass(SchoolClass schoolClass) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
        criteria.add(Restrictions.eq("schoolClass", schoolClass));
        return criteria.getExecutableCriteria(HibernateSessionFactoryUtil.getSessionFactory().openSession()).list();
    }

    public List getAll()
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From students").list();
    }
}
