package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class WorkDaoImpl  implements WorkDao{
    private SessionFactory sessionFactory;

    public WorkDaoImpl() { }
    @Autowired
    public WorkDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Work addWork(Work work) {
        sessionFactory.getCurrentSession().save(work);
        return work;
    }

    @Override
    public Work updateWork(Work work) {
        sessionFactory.getCurrentSession().update(work);
        return work;
    }

    @Override
    public void deleteWork(Work work) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(work);
        session.flush();
    }

    @Override
    public Work getWork(long id) {
        return sessionFactory.getCurrentSession().byId(Work.class).load(id);
    }

    @Override
    public Work getWork(String name) {
        return  sessionFactory.getCurrentSession().bySimpleNaturalId(Work.class).load(name);
    }

    @Override
    public List<Work> getAllWork() {
        return  sessionFactory.getCurrentSession().createQuery("from Work").list();
    }
}
