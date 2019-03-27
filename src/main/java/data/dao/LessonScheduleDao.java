package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.Discipline;
import data.entity.LessonSchedule;
import data.entity.Point;
import data.entity.SchoolClass;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LessonScheduleDao {
    public LessonSchedule findById(int id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(LessonSchedule.class, id);
    }

    public void save(LessonSchedule c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    public void update(LessonSchedule c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(c);
        transaction.commit();
        session.close();
    }

    public void delete(LessonSchedule c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(c);
        transaction.commit();
        session.close();
    }

    public List getLesson(SchoolClass schoolClass, int semestr, int lessonNumber, int day)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(LessonSchedule.class);
        criteria.add(Restrictions.eq("lessonNumber", lessonNumber));
        criteria.add(Restrictions.eq("schoolClass", schoolClass));
        criteria.add(Restrictions.eq("semestr", semestr));
        criteria.add(Restrictions.eq("day", day));
        return criteria.getExecutableCriteria(HibernateSessionFactoryUtil.getSessionFactory().openSession()).list();
    }

    public List<LessonSchedule> getLessonByWeek(SchoolClass schoolClass, Discipline discipline)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(LessonSchedule.class);
        criteria.add(Restrictions.eq("discipline", discipline));
        criteria.add(Restrictions.eq("schoolClass", schoolClass));
        return criteria.getExecutableCriteria(HibernateSessionFactoryUtil.getSessionFactory().openSession()).list();
    }

    public List getLessonByDay(SchoolClass schoolClass, int semestr, int lessonNumber)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(LessonSchedule.class);
        criteria.add(Restrictions.eq("lessonNumber", lessonNumber));
        criteria.add(Restrictions.eq("schoolClass", schoolClass));
        criteria.add(Restrictions.eq("semestr", semestr));
        return criteria.getExecutableCriteria(HibernateSessionFactoryUtil.getSessionFactory().openSession()).list();
    }

    public List getAll()
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From lesson_schedule").list();
    }
}
