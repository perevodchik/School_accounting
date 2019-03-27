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

        public List getBySchoolClass(SchoolClass schoolClass)
        {
            DetachedCriteria criteria = DetachedCriteria.forClass(Discipline.class);
            criteria.add(Restrictions.eq("schoolClass", schoolClass));
            return criteria.getExecutableCriteria(HibernateSessionFactoryUtil.getSessionFactory().openSession()).list();
        }

        public List getAll()
        {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From discipline").list();
        }
}
