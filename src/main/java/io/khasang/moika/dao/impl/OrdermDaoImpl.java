package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrdermDao;
import io.khasang.moika.entity.Orderm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrdermDaoImpl implements OrdermDao {
    private SessionFactory sessionFactory;

    public OrdermDaoImpl() {
    }

    @Autowired
    public OrdermDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Orderm addOrderm(Orderm orderm) {
        sessionFactory.getCurrentSession().save(orderm);
        return orderm;
    }

    @Override
    public Orderm updateOrderm(Orderm orderm) {
        sessionFactory.getCurrentSession().update(orderm);
        return orderm;
    }

    @Override
    public void deleteOrderm(Orderm orderm) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(orderm);
        session.flush();
    }

    @Override
    public Orderm getOrderm(long id) {
        List l = sessionFactory.getCurrentSession().createQuery("from Orderm where id =id").list();
        Object ob = l.get(0);
        return (Orderm) ob;
    }

    @Override
    public List<Orderm> getAllOrderm() {
        return sessionFactory.getCurrentSession().createQuery("from Orderm rder").list();
    }
}
