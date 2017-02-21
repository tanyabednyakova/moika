package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.SomeRowDAO;
import io.khasang.moika.entity.SomeRow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class SomeRowDAOImpl implements SomeRowDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public SomeRowDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SomeRow get(long id) {
        return sessionFactory.getCurrentSession().get(SomeRow.class,id);
        /*Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<SomeRow> criteria = cb.createQuery(SomeRow.class);
        Root<SomeRow> root= criteria.from(SomeRow.class);
        criteria.select(root)
                .where(cb.equal(root.get("id"),Long.toString(id)));
        return session.createQuery(criteria).getSingleResult();*/
    }

    @Override
    public SomeRow get(String name) {
        /*Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<SomeRow> criteria = session.getCriteriaBuilder()
                .createQuery(SomeRow.class);
        criteria.select(criteria.from(SomeRow.class))
                .where(session.getCriteriaBuilder()
                        .equal(criteria.from(SomeRow.class).get("name"),name));
        return session.createQuery(criteria).getSingleResult();*/
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<SomeRow> criteria = cb.createQuery(SomeRow.class);
        Root<SomeRow> root= criteria.from(SomeRow.class);
        criteria.select(root)
                .where(cb.equal(root.get("name"),name));
        return session.createQuery(criteria).getSingleResult();
    }

    @Override
    public void save(SomeRow someRow) {
        sessionFactory.getCurrentSession().save(someRow);
    }

    @Override
    public void update(SomeRow someRow) {
        sessionFactory.getCurrentSession().update(someRow);
    }

    @Override
    public List<SomeRow> getAllSomeRow() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<SomeRow> criteria = session.getCriteriaBuilder()
                .createQuery(SomeRow.class);
        criteria.select(criteria.from(SomeRow.class));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public void delete(SomeRow someRow) {
        sessionFactory.getCurrentSession().delete(someRow);
    }

    @Override
    public long countRow() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<Long> criteria = session.getCriteriaBuilder()
                .createQuery(Long.class);
        criteria.select(session.getCriteriaBuilder().count(criteria.from(SomeRow.class)));
        return session.createQuery(criteria).getSingleResult();
    }
}
