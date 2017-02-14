package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.SomeTableDAO;
import io.khasang.moika.entity.SomeTable;
import io.khasang.moika.sometest.SomeTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by blajimir on 14.02.2017.
 */
@Repository
@Transactional
public class SomeTableDAOImpl implements SomeTableDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public SomeTableDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SomeTable get(long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<SomeTable> criteria = session.getCriteriaBuilder()
                .createQuery(SomeTable.class);
        criteria.select(criteria.from(SomeTable.class))
                .where(session.getCriteriaBuilder()
                        .equal(criteria.from(SomeTable.class).get("id"),Long.toString(id)));
        return session.createQuery(criteria).getSingleResult();
    }

    @Override
    public void save(SomeTest someTable) {
        sessionFactory.getCurrentSession().save(someTable);
    }

    @Override
    public void update(SomeTest someTable) {
        sessionFactory.getCurrentSession().update(someTable);
    }

    @Override
    public List<SomeTable> getAllSomeTable() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<SomeTable> criteria = session.getCriteriaBuilder()
                .createQuery(SomeTable.class);
        criteria.select(criteria.from(SomeTable.class));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public void delete(SomeTest someTable) {
        sessionFactory.getCurrentSession().delete(someTable);
    }
}
