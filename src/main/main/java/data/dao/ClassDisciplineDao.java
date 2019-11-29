package data.dao;

import data.HibernateSessionFactoryUtil;
import data.entity.ClassDiscipline;
import data.entity.Discipline;
import data.entity.SchoolClass;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClassDisciplineDao {

    public void save(ClassDiscipline c)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
    }

    public void delete(SchoolClass schoolClass, Discipline discipline) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ClassDiscipline.class);
        criteria.add(Restrictions.eq("schoolClass", schoolClass));
        criteria.add(Restrictions.eq("discipline", discipline));

        List disciplineList = criteria.getExecutableCriteria(HibernateSessionFactoryUtil.getSessionFactory().openSession()).list();

        if(!disciplineList.isEmpty()) {
            ClassDiscipline classDiscipline = (ClassDiscipline) disciplineList.get(0);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(classDiscipline);
            transaction.commit();
            session.close();
        }
    }

}
