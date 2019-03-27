package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.Discipline;
import data.entity.Point;
import data.entity.SchoolClass;
import data.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class PointDao {

    public Point findById(int id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Point.class, id);
    }

    public void save(Point c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    public void update(Point c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(c);
        transaction.commit();
        session.close();
    }

    public void delete(Point c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(c);
        transaction.commit();
        session.close();
    }

    public List getStudentPointWithDiscipline(Student student, Discipline discipline, int semestr)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Point.class);
        criteria.add(Restrictions.eq("student", student));
        criteria.add(Restrictions.eq("discipline", discipline));
        criteria.add(Restrictions.eq("semestr", semestr));
        return criteria.getExecutableCriteria(HibernateSessionFactoryUtil.getSessionFactory().openSession()).list();
    }

    public List getPointWithDay(Student student, Discipline discipline, java.sql.Date date, SchoolClass schoolClass)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Point.class);
        criteria.add(Restrictions.eq("student", student));
        criteria.add(Restrictions.eq("discipline", discipline));
        criteria.add(Restrictions.eq("date", date));
        return criteria.getExecutableCriteria(HibernateSessionFactoryUtil.getSessionFactory().openSession()).list();
    }

    public List getAll()
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From points").list();
    }
}
