package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("WorkDao")
public class WorkDaoImpl  implements WorkDao{
    private SessionFactory sessionFactory;

    public WorkDaoImpl() { }
    @Autowired
    public WorkDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addWork(Work work) {
        sessionFactory.getCurrentSession().save(work);
    }

    @Override
    public void updateWork(Work work) {
        sessionFactory.getCurrentSession().update(work);
    }

    @Override
    public void deleteWork(Work work) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(work);
        session.flush();
    }

    @Override
    public Work getWork(long id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().createCriteria(Work.class);
        criteria.add(Restrictions.eq("id", id));
        return (Work) criteria.uniqueResult();
    }

    @Override
    public Work getWork(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Work.class);
        criteria.add(Restrictions.eq("name", name));
        return (Work) criteria.uniqueResult();
    }

    @Override
    public List<Work> getAllWork() {
        return  sessionFactory.getCurrentSession().createQuery("from work").list();
    }
}
