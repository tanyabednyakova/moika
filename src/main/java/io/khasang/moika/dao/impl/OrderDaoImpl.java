package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrderDao;
import io.khasang.moika.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("OrderDao")
public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory;

    public OrderDaoImpl() {
    }
    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order addOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        sessionFactory.getCurrentSession().update(order);
        return order;
    }

    @Override
    public void deleteOrder(Order order) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(order);
        session.flush();
    }

    @Override
    public Order getOrder(long id) {
        return null;
        // return (Order) sessionFactory.getCurrentSession().createQuery("from order where id =id");
    }

    @Override
    public List<Order> getAllOrder() {
        return  sessionFactory.getCurrentSession().createQuery("from order").list();
    }
}
