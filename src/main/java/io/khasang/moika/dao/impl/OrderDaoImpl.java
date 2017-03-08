package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrderDao;
import io.khasang.moika.entity.Ordern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory;

    public OrderDaoImpl() {
    }
    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ordern addOrder(Ordern ordern) {
        sessionFactory.getCurrentSession().save(ordern);
        return ordern;
    }

    @Override
    public Ordern updateOrder(Ordern ordern) {
        sessionFactory.getCurrentSession().update(ordern);
        return ordern;
    }

    @Override
    public void deleteOrder(Ordern ordern) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(ordern);
        session.flush();
    }

    @Override
    public Ordern getOrder(long id) {
        return null;
        // return (Ordern) sessionFactory.getCurrentSession().createQuery("from order where id =id");
    }

    @Override
    public List<Ordern> getAllOrder() {
        return  sessionFactory.getCurrentSession().createQuery("from Ordern rder").list();
    }
}
