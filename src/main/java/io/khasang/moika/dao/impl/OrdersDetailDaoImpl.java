package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrdersDetailDao;
import io.khasang.moika.entity.OrdersDetail;
import io.khasang.moika.entity.Work;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
@Transactional
public class OrdersDetailDaoImpl implements OrdersDetailDao {
    private SessionFactory sessionFactory;

    public OrdersDetailDaoImpl() {
    }

    @Autowired
    public OrdersDetailDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrdersDetail(OrdersDetail ordersDetail) {
        sessionFactory.getCurrentSession().save(ordersDetail);
    }

    @Override
    public void updateOrdersDetail(OrdersDetail ordersDetail) {
        sessionFactory.getCurrentSession().update(ordersDetail);
    }

    @Override
    public void deleteOrdersDetailk(OrdersDetail ordersDetail) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(ordersDetail);
        session.flush();
    }

    @Override
    public OrdersDetail getOrdersDetail(long id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().createCriteria(OrdersDetail.class);
        criteria.add(Restrictions.eq("id", id));
        return (OrdersDetail) criteria.uniqueResult();
    }

    @Override
    public OrdersDetail fillOrdersDetail(OrdersDetail ordersDetail, Work work, BigDecimal quantity) {
        ordersDetail.setQuantity(quantity);
        ordersDetail.setSumOfWork(work.getPrice().multiply(quantity));
//        ordersDetail.setWork(work);
        work.getOrdersDetails().add(ordersDetail);
        return ordersDetail;
    }

    @Override
    public List<OrdersDetail> getOrdersDetailForOrder(long idOrder) {
//        Query query  = sessionFactory.getCurrentSession().createQuery("from OrdersDetail where order = ?");
//        query.setParameter(0, idOrder);
//        return query.list();
        return null;
    }

    @Override
    public List<OrdersDetail> getAllOrdersDetail() {
        return  sessionFactory.getCurrentSession().createQuery("from OrdersDetail ").list();
    }
}
